package com.prince.course.repository;

import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Integer deleteCourseByUser(User user);
    List<Course> findAllByUser(User user);

}
