package com.prince.video_service.services;


import com.prince.common.data.dtos.UploadResponseDto;
import com.prince.common.data.dtos.UploadVideoDto;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.Video;
import com.prince.data.commons.dtos.VideoDetailsDto;
import com.prince.video_service.Feign.FeignClientCourse;
import com.prince.video_service.repositories.VideoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.prince.data.commons.exceptions.ResourNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final VideoProcessing videoProcessing;
    private final FeignClientCourse feignClientCourse;

    ModelMapper modelMapper;
    @Value("${video.path}")
    private String DIR;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, VideoProcessing videoProcessing, ModelMapper modelMapper, FeignClientCourse feignClientCourse) {
        this.videoRepository = videoRepository;
        this.videoProcessing = videoProcessing;
        this.modelMapper = modelMapper;
        this.feignClientCourse = feignClientCourse;
    }


    @PostConstruct
    public void init() {
        try {
            Path path = Paths.get(DIR);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
                System.out.println("Directory created: " + DIR);
            } else {
                System.out.println("Directory already exists: " + DIR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + DIR, e);
        }
    }

    @Override
    @Transactional
    public UploadResponseDto saveVideo(UploadVideoDto uploadVideoDto, MultipartFile file, Long courseId) {
        Course course = feignClientCourse.getCourseDetails(courseId).orElseThrow(()-> new ResourNotFoundException("Coruse", "coruseId", courseId));
        return videoProcessing.videoProcessAndStore(uploadVideoDto, file, course);
    }

    public List<VideoDetailsDto> getAllVideos() {
        List<Video> allVideos = videoRepository.findAll();
        List<VideoDetailsDto> videoDetails;
        videoDetails = allVideos.stream()
                .map(video -> modelMapper.map(video, VideoDetailsDto.class))
                .collect(Collectors.toList());
        return videoDetails;
    }

    public VideoDetailsDto getVideoDetails(Long id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ResourNotFoundException("VideoService","videoId", id));
        return modelMapper.map(video, VideoDetailsDto.class);
    }

    public String getPath(Long id){
        return videoRepository.findPathById(id).orElseThrow(() -> new ResourNotFoundException("VideoService", "videoId", id));
    }

    @Override
    @Transactional
    public boolean deleteVideo(Long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
