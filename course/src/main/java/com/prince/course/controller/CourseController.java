package com.prince.course.controller;

import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.User;
import com.prince.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<CourseDetailsDto> createCourse(CourseDto courseDto){
        CourseDetailsDto res = courseService.createCourse(courseDto);
        return ResponseEntity.ok()
                .body(res);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailsDto> getCourseDetails(@PathVariable Long courseId){
        CourseDetailsDto res = courseService.getCourseDetails(courseId);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
   }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<String>("Deleted", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteCourseByUser(User user){
        Integer res = courseService.deleteCourseByUser(user);
        return new ResponseEntity<Integer>(res, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDetailsDto>> getAllCourses(){
        List<CourseDetailsDto> allCourses = courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }


}
