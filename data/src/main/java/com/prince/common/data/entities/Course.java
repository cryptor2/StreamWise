package com.prince.common.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    private String courseDescription;

    @CreationTimestamp
    @Column(updatable = false, name = "creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Video> videoList = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "instructor_id")
    private User user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    Set<CourseRegistration> registrations = new HashSet<>();
}
