package com.prince.common.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @CreationTimestamp
    @Column(updatable = false, name = "registered_at")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
}