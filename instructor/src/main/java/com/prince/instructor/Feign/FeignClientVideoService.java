package com.prince.instructor.Feign;


import com.prince.common.data.dtos.UploadResponseDto;
import com.prince.common.data.dtos.UploadVideoDto;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(url = "http://localhost:8084/", name = "video-service")
public interface FeignClientVideoService {
    @PostMapping(value = "/upload-video", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<UploadResponseDto> videoUpload(
            @RequestPart("uploadVideoDto") UploadVideoDto uploadVideoDto,
            @RequestPart("file") MultipartFile file,
            @RequestParam("courseId") Long courseId
    );
}