package com.example.sports.services.impl;

import com.example.sports.domain.dto.UserDto;
import com.example.sports.domain.entities.Role;
import com.example.sports.domain.entities.User;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.UserRepository;
import com.example.sports.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);

        // Null Checks
        if(user.getId() != null)
            throw new IllegalArgumentException("User already has an Id");
        if(user.getName() == null || user.getName().isBlank())
            throw new IllegalArgumentException("User name must be specified");
        if(user.getEmail() == null || user.getEmail().isBlank())
            throw new IllegalArgumentException("User email must be specified");
        if(user.getPassword() == null || user.getPassword().isBlank())
            throw new IllegalArgumentException("User must provide a password");
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new IllegalArgumentException("User email already exists");

        return UserMapper.INSTANCE.userToUserDto(
                userRepository.save(new User(
                        null,
                        user.getName(),
                        user.getEmail(),
                        passwordEncoder.encode(user.getPassword()),
                        user.getBranch(),
                        Role.STUDENT,
                        null,
                        null
                ))
        );
    }

    @Override
    public UserDto getUser(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("userId is Invalid"));

        return UserMapper.INSTANCE.userToUserDto(user);
    }
}

