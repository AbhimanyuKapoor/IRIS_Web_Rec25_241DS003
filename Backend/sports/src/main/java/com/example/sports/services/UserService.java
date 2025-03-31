package com.example.sports.services;

import com.example.sports.domain.dto.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(UUID userId);

    // Default Admin is created when an empty DB is initialized
    void createDefaultAdmin();
}
