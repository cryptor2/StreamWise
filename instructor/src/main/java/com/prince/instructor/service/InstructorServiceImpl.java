package com.prince.instructor.service;

import com.prince.common.data.entities.Course;
import com.prince.data.commons.exceptions.ResourNotFoundException;
import com.prince.common.data.entities.User;
import com.prince.instructor.Feign.FeignClientUserService;
import com.prince.instructor.Feign.FeignClientCourse;
import com.prince.instructor.Feign.FeignClientVideoService;
import com.prince.common.data.dtos.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final ModelMapper modelMapper;
    private final FeignClientUserService feignClientAuthApi;
    private final FeignClientCourse feignClientCourse;

    private final FeignClientVideoService feignClientVideoService;
//

    public InstructorServiceImpl(ModelMapper modelMapper, FeignClientUserService feignClientAuthApi, FeignClientCourse feignClientCourse, FeignClientVideoService feignClientVideoService) {
        this.modelMapper = modelMapper;
        this.feignClientAuthApi = feignClientAuthApi;
        this.feignClientCourse = feignClientCourse;
        this.feignClientVideoService = feignClientVideoService;
    }


//    public ResponseInstructorDto findInstructorDetails(Long userId){
//        UserDto userDto = feignClientAuthApi.userDetails(userId).orElseThrow(()->new ResourNotFoundException("Instructor", "userId", userId));
//        ResponseInstructorDto responseInstructorDto = modelMapper.map(userDto, ResponseInstructorDto.class);
//        List<CourseDetailsDto> courseDetailsDto = feignClientCourse.getCoursesByUser(userId).getBody();
//        responseInstructorDto.setCourseList(courseDetailsDto);
//        return responseInstructorDto;
//    }

//    @Transactional
//    public ResponseEntity<CourseDetailsDto> createCourse(CreateCourseDto createCourseDto, Long userId){
//        UserDto userDto = feignClientAuthApi.userDetails(userId).orElseThrow(()->new ResourNotFoundException("Instructor", "userId", userId));
//        CourseDto courseDto = modelMapper.map(createCourseDto, CourseDto.class);
//        courseDto.setUser(modelMapper.map(userDto, User.class));
//        return feignClientCourse.createCourse(courseDto);
//    }


    @Transactional
    public ResponseEntity<UploadResponseDto> uploadVideo(UploadVideoDto uploadVideoDto, MultipartFile file){
        feignClientAuthApi.userDetails(uploadVideoDto.getUserId()).orElseThrow(()->new ResourNotFoundException("Instructor", "userId", uploadVideoDto.getUserId()));
        return feignClientVideoService.videoUpload(uploadVideoDto, file);
    }

//    public ResponseEntity<String> deleteCourse(Long userId, Long courseId){
//        Course course = feignClientCourse.getCourseDetails(courseId).getBody().orElseThrow(()-> new ResourNotFoundException("Course", "courseId", courseId));;
//        if(course.getUser().getId().equals(userId)){
//            return new ResponseEntity<>("Failed", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
//        }
//        return feignClientCourse.deleteCourse(courseId);
//    }

//    public ResponseEntity<Integer> deleteAllCourse(Long userId){
//        UserDto userDto = feignClientAuthApi.userDetails(userId).orElseThrow(()->new ResourNotFoundException("user", "userId", userId));
//        User user = modelMapper.map(userDto, User.class);
//        return feignClientCourse.deleteCourseByUser(user);
//    }
}
