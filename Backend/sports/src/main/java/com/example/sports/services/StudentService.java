package com.example.sports.services;

import com.example.sports.domain.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(UUID userId);
}
