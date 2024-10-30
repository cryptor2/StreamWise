package com.prince.video_service.services;


import com.prince.common.data.dtos.UploadResponseDto;
import com.prince.common.data.dtos.UploadVideoDto;
import org.springframework.web.multipart.MultipartFile;
import com.prince.data.commons.dtos.VideoDetailsDto;
import java.util.List;

public interface VideoService {
    UploadResponseDto saveVideo(UploadVideoDto uploadVideoDto, MultipartFile file);

    List<VideoDetailsDto> getAllVideos();
    List<VideoDetailsDto> getCourseVideos(Long courseId);
    VideoDetailsDto getVideoDetails(Long id);

    String getPath(Long id);

    boolean deleteVideo(Long id);
}
