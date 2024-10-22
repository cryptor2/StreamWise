package com.prince.videostreaming.user_service.service;

import com.prince.common.data.dtos.UserDto;
import com.prince.common.data.entities.User;
import com.prince.videostreaming.user_service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prince.data.commons.exceptions.ResourNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> allUsers() {
        List<UserDto> users = new ArrayList<>();

        users = userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return users;
    }

    public UserDto getUserDetails(Long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new ResourNotFoundException("auth", "userId", userId)));
        return modelMapper.map(user, UserDto.class);
    }
}