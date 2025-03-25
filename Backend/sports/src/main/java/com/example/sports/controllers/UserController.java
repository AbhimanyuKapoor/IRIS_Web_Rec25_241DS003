package com.example.sports.controllers;

import com.example.sports.domain.dto.UserDto;
import com.example.sports.repositories.UserRepository;
import com.example.sports.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

// To get details about the authenticated User

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/role")
    public String getUserRole(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getAuthorities().iterator().next().getAuthority();
    }

    @GetMapping(path = "/details")
    public UserDto getUserDetails(@RequestAttribute UUID userId) {
        return userService.getUser(userId);
    }
}
