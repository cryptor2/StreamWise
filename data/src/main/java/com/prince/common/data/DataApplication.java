package com.prince.common.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.prince.common.data.entities")
public class DataApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}
}
