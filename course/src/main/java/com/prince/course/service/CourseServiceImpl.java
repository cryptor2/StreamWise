package com.prince.course.service;

import com.prince.common.data.dtos.*;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.User;
import com.prince.course.Feign.FeignClientUserService;
import com.prince.course.repository.CourseRepository;
import com.prince.data.commons.exceptions.ResourNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    ModelMapper modelMapper;
    CourseRepository courseRepository;
    FeignClientUserService feignClientUserService;

    @Autowired
    public CourseServiceImpl(ModelMapper modelMapper, CourseRepository courseRepository, FeignClientUserService feignClientUserService) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
        this.feignClientUserService =feignClientUserService;
    }

    User getUser(Long userId){
        UserDto userDto = feignClientUserService.userDetails(userId).orElseThrow(()->new ResourNotFoundException("CourseService", "userId", userId));
        return modelMapper.map(userDto, User.class);
    }

    @Override
    @Transactional
    public CourseDetailsDto createCourse(CreateCourseDto createCourseDto) {
        Course course = modelMapper.map(createCourseDto, Course.class);
        course.setUser(this.getUser(createCourseDto.getUserId()));
        Course response = courseRepository.save(course);
        return modelMapper.map(response, CourseDetailsDto.class);
    }


    @Override
    public ResponseInstructorDto getCourseByInstructor(Long instructorId) {
        User user = this.getUser(instructorId);
        List<Course> courses = courseRepository.findAllByUser(user);
        List<CourseDetailsDto> allCourses;
        allCourses = courses.stream()
                .map(course -> modelMapper.map(course, CourseDetailsDto.class))
                .collect(Collectors.toList());
        ResponseInstructorDto responseInstructorDto = modelMapper.map(user, ResponseInstructorDto.class);
        responseInstructorDto.setCourseList(allCourses);
        responseInstructorDto.setInstructorId(instructorId);
        return responseInstructorDto;
    }

    public CourseDto getCourseDetails(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourNotFoundException("Course", "courseId", courseId));
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        courseDto.setInstructorId(course.getUser().getId());
        return courseDto;
    }


    @Transactional
    public void deleteCourse(Long userId, Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent() && course.get().getUser().getId().equals(userId)) {
            courseRepository.delete(course.get());
        } else {
            throw new ResourceAccessException("Course not found or does not belong to the specified user.");
        }
    }

    public Integer deleteCourseByUser(Long userId){
        User user = this.getUser(userId);
        return courseRepository.deleteCoursesByUser(user);
    }

    public List<CourseDetailsDto> getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        List<CourseDetailsDto> allCourses;
        allCourses = courses.stream()
                .map(course -> modelMapper.map(course, CourseDetailsDto.class))
                .collect(Collectors.toList());
        return allCourses;
    }
}
