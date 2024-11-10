package com.prince.video_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.prince.common.data.entities")
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

}
