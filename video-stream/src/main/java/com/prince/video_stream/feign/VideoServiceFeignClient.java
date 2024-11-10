package com.prince.video_stream.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "VIDEO-SERVICE")
public interface VideoServiceFeignClient {
    @GetMapping("video/path/{id}")
    ResponseEntity<String> getVideoPath(@PathVariable Long id);
}
