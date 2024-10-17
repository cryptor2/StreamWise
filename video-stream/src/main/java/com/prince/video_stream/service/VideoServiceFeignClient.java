package com.prince.video_stream.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081/", value = "video-service-client")
public interface VideoServiceFeignClient {
    @GetMapping("/video/path/{id}")
    String getPath(@PathVariable Long id);
}
