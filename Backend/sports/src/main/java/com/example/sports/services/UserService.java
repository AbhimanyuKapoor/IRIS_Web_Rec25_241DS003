package com.example.sports.services;

import com.example.sports.domain.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(UUID userId);
}
