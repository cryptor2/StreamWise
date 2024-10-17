package com.prince.common.data.dtos;

import com.prince.common.data.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    private String courseName;
    private String courseDescription;
    private User user;
}
