package com.prince.instructor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin
@EntityScan(basePackages = "com.prince.common.data.entities")
public class InstructorApplication {
	public static void main(String[] args) {
		SpringApplication.run(InstructorApplication.class, args);
	}
}
