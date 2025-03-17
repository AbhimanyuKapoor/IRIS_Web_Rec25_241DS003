package com.example.sports.domain.dto;

public record LoginRequest(
        String email,
        String password
) {
}
