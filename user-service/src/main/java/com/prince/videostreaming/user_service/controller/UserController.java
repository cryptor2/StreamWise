package com.prince.videostreaming.user_service.controller;

import com.prince.common.data.dtos.UserDto;
import com.prince.common.data.entities.User;
import com.prince.videostreaming.user_service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(modelMapper.map(currentUser, UserDto.class));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> allUsers() {
        List<UserDto> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> userDetails(@PathVariable Long userId) {
        UserDto user = userService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }
}