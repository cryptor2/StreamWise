package com.prince.student.Feign;

import com.prince.common.data.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE", value = "auth-api-client")
public interface FeignClientUserService {
    @GetMapping("/users/{userId}")
    Optional<UserDto> userDetails(@PathVariable Long userId);
}
