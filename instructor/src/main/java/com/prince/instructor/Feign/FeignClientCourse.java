package com.prince.instructor.Feign;

import com.prince.common.data.entities.User;
import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "COURSE")
public interface FeignClientCourse {
    @GetMapping("/course/{courseId}")
    ResponseEntity<Optional<Course>> getCourseDetails(@PathVariable Long courseId);
    @GetMapping("/course/createdBy/{userId}")
    ResponseEntity<List<CourseDetailsDto>> getCoursesByUser(@PathVariable Long userId);
    @PostMapping("/course")
    ResponseEntity<CourseDetailsDto> createCourse(@RequestBody CourseDto courseDto);

    @DeleteMapping("/course/{courseId}")
    ResponseEntity<String> deleteCourse(@PathVariable Long courseId);

    @DeleteMapping("/user")
    ResponseEntity<Integer> deleteCourseByUser(User user);
}