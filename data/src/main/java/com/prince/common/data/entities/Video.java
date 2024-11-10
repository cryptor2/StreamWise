package com.prince.common.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "videos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @Column(nullable = false, length = 50)
    private String videoTitle;

    @Column(nullable = false, length = 100)
    private String videoDescription;

    @Column(nullable = false, length = 10)
    private String videoFormat;

    @Column(nullable = false, length = 150)
    private String videoPath;

    @Column(nullable = false)
    private Long fileSize;

    @CreationTimestamp
    @Column(updatable = false, name = "upload_date")
    private Date uploadDate;

    @ManyToOne()
    @JoinColumn(name = "courseId")
    private Course course;
}
