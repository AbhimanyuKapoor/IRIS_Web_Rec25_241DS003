package com.example.sports.services.impl;

import com.example.sports.domain.dto.UserDto;
import com.example.sports.domain.entities.Role;
import com.example.sports.domain.entities.User;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.UserRepository;
import com.example.sports.services.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);

        if(null != user.getId())
            throw new IllegalArgumentException("User already has an Id");
        if(null == user.getName() || user.getName().isBlank())
            throw new IllegalArgumentException("User name must be specified");
        if(null == user.getEmail() || user.getEmail().isBlank())
            throw new IllegalArgumentException("User email must be specified");
        if(null == user.getPassword() || user.getPassword().isBlank())
            throw new IllegalArgumentException("User must provide a password");

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

