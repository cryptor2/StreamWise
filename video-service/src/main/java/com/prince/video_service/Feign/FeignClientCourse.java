package com.prince.video_service.Feign;


import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "COURSE")
public interface FeignClientCourse {
    @GetMapping("/course/{courseId}")
    ResponseEntity<CourseDto> getCourseDetails(@PathVariable Long courseId);
}