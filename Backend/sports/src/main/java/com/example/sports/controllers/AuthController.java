package com.example.sports.controllers;

import com.example.sports.domain.dto.AuthResponse;
import com.example.sports.domain.dto.LoginRequest;
import com.example.sports.domain.dto.UserDto;
import com.example.sports.services.AuthenticationService;
import com.example.sports.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final StudentService studentService;

    public AuthController(AuthenticationService authenticationService, StudentService studentService) {
        this.authenticationService = authenticationService;
        this.studentService = studentService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = studentService.createUser(userDto);

        UserDetails userDetails = authenticationService.authenticate(
                userDto.email(),
                userDto.password()
        );

        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(
                tokenValue,
                86400,
                "Signup Successful"
        );

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(
                loginRequest.email(),
                loginRequest.password()
        );

        String tokenValue = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(
                tokenValue,
                86400,
                "Login Successful"
        );

        return ResponseEntity.ok(authResponse);
    }
}
