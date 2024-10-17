package com.prince.course.service;

import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.User;
import com.prince.course.repository.CourseRepository;
import com.prince.data.commons.exceptions.ResourNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{
    ModelMapper modelMapper;
    CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(ModelMapper modelMapper, CourseRepository courseRepository) {
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public CourseDetailsDto createCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        Course response = courseRepository.save(course);
        return modelMapper.map(response, CourseDetailsDto.class);
    }
    public CourseDetailsDto getCourseDetails(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourNotFoundException("Course", "courseId", courseId));
        return modelMapper.map(course, CourseDetailsDto.class);
    }

    public void deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
    }

    public Integer deleteCourseByUser(User user){
        return courseRepository.deleteCourseByUser(user);
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
