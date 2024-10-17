package com.prince.instructor.service;

import com.prince.common.data.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface InstructorService {
    ResponseInstructorDto findInstructorDetails(Long userId);
    ResponseEntity<CourseDetailsDto> createCourse(CreateCourseDto createCourseDto, Long userId);
    ResponseEntity<UploadResponseDto> uploadVideo(UploadVideoDto uploadVideoDto, MultipartFile file, Long userId, Long courseId);
    ResponseEntity<String> deleteCourse(Long userId, Long courseId);
    ResponseEntity<Integer> deleteAllCourse(Long userId);
}
