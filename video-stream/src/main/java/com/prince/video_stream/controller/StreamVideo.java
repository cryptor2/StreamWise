package com.prince.video_stream.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface StreamVideo {
    ResponseEntity<Resource> sendMasterFile(Long videoId);
    ResponseEntity<Resource> sendMasterFile(Long videoId, String quality);
    ResponseEntity<Resource> sendSegment(Long videoId,String quality, String segment);
}
