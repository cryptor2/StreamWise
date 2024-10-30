package com.prince.video_service.services;


import com.prince.common.data.dtos.UploadResponseDto;
import com.prince.common.data.dtos.UploadVideoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    UploadResponseDto saveVideo(UploadVideoDto uploadVideoDto, MultipartFile file);

    List<com.prince.data.commons.dtos.VideoDetailsDto> getAllVideos();

    com.prince.data.commons.dtos.VideoDetailsDto getVideoDetails(Long id);

    String getPath(Long id);

    boolean deleteVideo(Long id);
}
