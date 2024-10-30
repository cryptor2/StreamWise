package com.prince.common.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseInstructorDto {
    private Long instructorId;
    private String userName;
    private String email;
    List<CourseDetailsDto> courseList = new ArrayList<>();
}
