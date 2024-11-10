package com.prince.video_service.services;


import com.prince.common.data.dtos.*;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.Video;
import com.prince.video_service.repositories.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class VideoProcessing {
    private final VideoRepository videoRepository;
    ModelMapper modelMapper;
    @Value("${video.path}")
    private String DIR;

    @Autowired
    public VideoProcessing(ModelMapper modelMapper, VideoRepository videoRepository) {
        this.modelMapper = modelMapper;
        this.videoRepository = videoRepository;
    }

    public UploadResponseDto videoProcessAndStore(UploadVideoDto uploadVideoDto, MultipartFile file, Course course) {
        UploadResponseDto uploadResponseDto = modelMapper.map(uploadVideoDto, UploadResponseDto.class);
        try {
            String videoFormat = file.getContentType();
            InputStream input = file.getInputStream();

            String uuid = UUID.randomUUID().toString();
            String rootDir = Files.createDirectory(Paths.get(StringUtils.cleanPath(DIR), uuid)).toString();
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            Path path = Paths.get(rootDir, fileName);
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);

            Path path360 = Paths.get(rootDir, "/360p");
            Path path480 = Paths.get(rootDir, "/480p");
            Path path720 = Paths.get(rootDir, "/720p");

//          Created files for different quality
            Files.createDirectory(path360);
            Files.createDirectory(path480);
            Files.createDirectory(path720);

            String[] command = {
                    "ffmpeg",
                    "-i", path.toString(),
                    "-filter:v:0", "scale=-2:360", "-b:v:0", "800k", "-maxrate:v:0", "856k", "-bufsize:v:0", "1200k",
                    "-hls_time", "10", "-hls_playlist_type", "vod", "-hls_segment_filename", path360.toString() + "/360p_%03d.ts",
                    "-f", "hls", rootDir.toString() + "/360p.m3u8",

                    "-filter:v:1", "scale=-2:480", "-b:v:1", "1400k", "-maxrate:v:1", "1498k", "-bufsize:v:1", "2100k",
                    "-hls_time", "10", "-hls_playlist_type", "vod", "-hls_segment_filename", path480.toString() + "/480p_%03d.ts",
                    "-f", "hls", rootDir.toString() + "/480p.m3u8",

                    "-filter:v:2", "scale=-2:720", "-b:v:2", "2800k", "-maxrate:v:2", "2996k", "-bufsize:v:2", "4200k",
                    "-hls_time", "10", "-hls_playlist_type", "vod", "-hls_segment_filename", path720.toString() + "/720p_%03d.ts",
                    "-f", "hls", rootDir.toString() + "/720p.m3u8"
            };

            // Execute the FFmpeg command
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Print the FFmpeg output to the console
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Generate the master playlist
            Path masterPlaylistPath = Paths.get(rootDir).resolve("master.m3u8");
            try (FileWriter writer = new FileWriter(masterPlaylistPath.toFile())) {
                writer.write("#EXTM3U\n\n");

                writer.write("#EXT-X-STREAM-INF:BANDWIDTH=800000,RESOLUTION=640x360\n");
                writer.write(path360.resolve("360p.m3u8").toString() + "\n\n");

                writer.write("#EXT-X-STREAM-INF:BANDWIDTH=1400000,RESOLUTION=842x480\n");
                writer.write(path480.resolve("480p.m3u8").toString() + "\n\n");

                writer.write("#EXT-X-STREAM-INF:BANDWIDTH=2800000,RESOLUTION=1280x720\n");
                writer.write(path720.resolve("720p.m3u8").toString() + "\n\n");


                System.out.println("Master playlist created at: " + masterPlaylistPath.toString());
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("HLS generation completed successfully.");
            } else {
                System.out.println("HLS generation failed with exit code " + exitCode);
            }
            Video video = new Video();
            video.setVideoTitle(uploadVideoDto.getVideoTitle());
            video.setVideoDescription(uploadVideoDto.getVideoDescription());
            video.setVideoPath(rootDir);
            video.setVideoFormat(videoFormat);
            video.setFileSize(file.getSize());
            video.setCourse(course);
            videoRepository.save(video);

            Files.deleteIfExists(path);

            uploadResponseDto.setMessage("Uploaded :)");
            uploadResponseDto.setIsUploaded(true);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            uploadResponseDto.setMessage("Upload Failed :( " + e.getMessage());
            uploadResponseDto.setIsUploaded(false);
        }
        return uploadResponseDto;
    }
}
