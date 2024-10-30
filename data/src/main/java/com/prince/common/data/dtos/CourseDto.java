package com.prince.common.data.dtos;

import com.prince.common.data.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    private Long instructorId;
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private Date creationDate;
}
