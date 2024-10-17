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

    @Column(nullable = false)
    private String videoTitle;

    @Column(nullable = false)
    private String videoDescription;

    @Column(nullable = false)
    private String videoFormat;

    @Column(nullable = false)
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
