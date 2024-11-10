package com.prince.course.controller;

import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.dtos.CreateCourseDto;
import com.prince.common.data.dtos.ResponseInstructorDto;

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

    @GetMapping("/createdBy/{userId}")
    public ResponseEntity<ResponseInstructorDto> getCoursesByUser(@PathVariable Long userId){
        ResponseInstructorDto courses = courseService.getCourseByInstructor(userId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourseDetails(@PathVariable Long courseId){
        CourseDto res = courseService.getCourseDetails(courseId);
        return new ResponseEntity<>(res, HttpStatus.OK);
   }


    @GetMapping("/all")
    public ResponseEntity<List<CourseDetailsDto>> getAllCourses(){
        List<CourseDetailsDto> allCourses = courseService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<CourseDetailsDto> createCourse(@RequestBody CreateCourseDto createCourseDto){
        CourseDetailsDto res = courseService.createCourse(createCourseDto);
        return ResponseEntity.ok()
                .body(res);
    }
    @DeleteMapping("/userId/{userId}/courseId/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId){
        courseService.deleteCourse(userId, courseId);
        return new ResponseEntity<>("Deleted", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Integer> deleteCourseByUser(@PathVariable Long userId){
        Integer res = courseService.deleteCourseByUser(userId);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}
