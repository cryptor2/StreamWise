package com.prince.student.Feign;

import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "COURSE")
public interface FeignClientCourse {
    @GetMapping("/course/{courseId}")
    ResponseEntity<CourseDto> getCourseDetails(@PathVariable Long courseId);
}