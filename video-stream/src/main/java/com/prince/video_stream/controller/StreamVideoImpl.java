package com.prince.video_stream.controller;

import com.prince.video_stream.service.StreamVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/play")
public class StreamVideoImpl implements StreamVideo{
    StreamVideoService streamVideoService;

    @Autowired
    public StreamVideoImpl(StreamVideoService streamVideoService) {
        this.streamVideoService = streamVideoService;
    }

    @GetMapping("/{videoId}/master-file")
    public ResponseEntity<Resource> sendMasterFile(@PathVariable Long videoId){
        Resource resource = streamVideoService.getMasterFile(videoId);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl"
                ).body(resource);
    }

    @GetMapping("/{videoId}/{quality}/m3u8")
    public ResponseEntity<Resource> sendMasterFile(@PathVariable Long videoId, @PathVariable String quality){
        Resource resource = streamVideoService.getMasterFile(videoId, quality);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE, "application/vnd.apple.mpegurl"
                ).body(resource);
    }

    @GetMapping("/{videoId}/{quality}/{segment}.ts")
    public ResponseEntity<Resource> sendSegment(@PathVariable Long videoId,@PathVariable String quality, @PathVariable String segment){
        Resource resource = streamVideoService.getSegment(videoId, quality, segment);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE, "video/mp2t"
                ).body(resource);
    }
}
