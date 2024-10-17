package com.prince.video_service.Feign;

import com.prince.common.data.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url = "http://localhost:8080/", value = "auth-api-client")
public interface FeignClientAuthApi {
    @GetMapping("/users/{userId}")
    Optional<UserDto> userDetails(@PathVariable Long userId);
}
