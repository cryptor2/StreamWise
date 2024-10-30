package com.prince.video_service.controller;


import com.prince.common.data.dtos.UploadResponseDto;
import com.prince.data.commons.dtos.VideoDetailsDto;
import com.prince.common.data.dtos.UploadVideoDto;
import com.prince.video_service.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class uploadVideoController {
    private final VideoService videoService;

    @Autowired
    public uploadVideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @PostMapping("/upload-video")
    public ResponseEntity<UploadResponseDto> videoUpload(UploadVideoDto uploadVideoDto, MultipartFile file) {
        UploadResponseDto res = videoService.saveVideo(uploadVideoDto, file);
        return new ResponseEntity<UploadResponseDto>(res, HttpStatus.CREATED);
    }

    @GetMapping("/all-videos")
    public ResponseEntity<List<VideoDetailsDto>> getAllVideos() {
        return new ResponseEntity<List<VideoDetailsDto>>(videoService.getAllVideos(), HttpStatus.OK);
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<VideoDetailsDto> getVideoDetail(@PathVariable Long id) {
        return new ResponseEntity<VideoDetailsDto>(videoService.getVideoDetails(id), HttpStatus.OK);
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        return new ResponseEntity<String>("DELETED", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/video/path/{id}")
    public ResponseEntity<String> getVideoPath(@PathVariable Long id) {
        String path = videoService.getPath(id);
        return new ResponseEntity<String>(path, HttpStatus.OK);
    }


}
