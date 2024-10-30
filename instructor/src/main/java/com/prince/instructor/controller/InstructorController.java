package com.prince.instructor.controller;

import com.prince.common.data.dtos.*;
import com.prince.instructor.service.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<ResponseInstructorDto> getInstructorDetails(@PathVariable Long userId){
//        ResponseInstructorDto instructorDto = instructorService.findInstructorDetails(userId);
//        return new ResponseEntity<>(instructorDto, HttpStatus.OK);
//    }

//    @PostMapping("/createCourse/{userId}")
//    public ResponseEntity<CourseDetailsDto> createCourse(@RequestBody CreateCourseDto courseDto, @PathVariable Long userId){
//        return instructorService.createCourse(courseDto, userId);
//
//    }
//
//    @PostMapping("/uploadVideo")
//    public ResponseEntity<UploadResponseDto> videoUpload(UploadVideoDto uploadVideoDto, MultipartFile file) {
//        return instructorService.uploadVideo(uploadVideoDto,file);
//    }
//
//    @DeleteMapping("/deleteCourse/{userId}/{courseId}")
//    public ResponseEntity<String> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId){
//        return instructorService.deleteCourse(userId,courseId);
//    }
//
//    @DeleteMapping("/deleteAllCourse/{userId}")
//    public ResponseEntity<Integer> deleteAllCourse(@PathVariable Long userId){
//        return instructorService.deleteAllCourse(userId);
//    }
}
