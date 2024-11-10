package com.prince.student.service;

import com.prince.common.data.dtos.ResponseStudentDto;

public interface StudentService {
    ResponseStudentDto findStudentDetails(Long userId);
    Boolean checkStudentRegisteredACourse(Long userId, Long courseId);
    String registerCourse(Long userId, Long courseId);
    String deleteCourseById(Long userId, Long courseId);
    Integer deleteByUserId(Long userId);
}
