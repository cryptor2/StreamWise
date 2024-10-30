package com.prince.common.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ACourseDetailsDto {
    private Long instructorId;
    private Long courseId;
    private String courseName;
    private String courseDescription;
    private Date creationDate;
}
