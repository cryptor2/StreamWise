package com.prince.course.service;

import com.prince.common.data.dtos.*;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.User;

import java.util.List;


public interface CourseService {
    CourseDetailsDto createCourse(CreateCourseDto createCourseDto);

    ResponseInstructorDto getCourseByInstructor(Long userId);
    CourseDto getCourseDetails(Long courseId);
    void deleteCourse(Long userId, Long courseId);
    Integer deleteCourseByUser(Long userId);

    List<CourseDetailsDto> getAllCourses();
}
