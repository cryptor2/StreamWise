package com.prince.student.repository;


import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.CourseRegistration;
import com.prince.common.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    List<CourseRegistration> findAllByUser(User user);
    Integer deleteByUserAndCourse(User user, Course course);
    Integer deleteByUser(User user);
}
