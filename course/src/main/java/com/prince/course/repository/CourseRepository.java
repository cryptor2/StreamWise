package com.prince.course.repository;

import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Integer deleteCourseByUser(User user);
}
