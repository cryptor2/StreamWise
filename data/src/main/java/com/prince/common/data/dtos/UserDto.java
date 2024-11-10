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
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private String password;

    private Date createdAt;

    private Date updatedAt;
}
