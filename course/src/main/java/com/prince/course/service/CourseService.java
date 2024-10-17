package com.prince.course.service;

import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.entities.User;

import java.util.List;


public interface CourseService {
    CourseDetailsDto createCourse(CourseDto courseDto);
    CourseDetailsDto getCourseDetails(Long courseId);
    void deleteCourse(Long id);
    Integer deleteCourseByUser(User user);

    List<CourseDetailsDto> getAllCourses();
}
