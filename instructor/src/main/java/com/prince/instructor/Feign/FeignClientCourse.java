package com.prince.instructor.Feign;

import com.prince.common.data.entities.User;
import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


@FeignClient(name = "course")
public interface FeignClientCourse {
    @GetMapping("/course/{courseId}")
    ResponseEntity<Optional<Course>> getCourseDetails(@PathVariable Long courseId);

    @PostMapping("/course")
    ResponseEntity<CourseDetailsDto> createCourse(CourseDto courseDto);

    @DeleteMapping("/{courseId}")
    ResponseEntity<String> deleteCourse(@PathVariable Long courseId);

    @DeleteMapping("/user")
    ResponseEntity<Integer> deleteCourseByUser(User user);
}