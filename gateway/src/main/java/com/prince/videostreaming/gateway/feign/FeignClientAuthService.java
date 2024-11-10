package com.prince.videostreaming.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="AUTH-SERVICE")
public interface FeignClientAuthService {
    @GetMapping("auth/validate")
    ResponseEntity<String> validateToken(@RequestParam("token") String token);
}