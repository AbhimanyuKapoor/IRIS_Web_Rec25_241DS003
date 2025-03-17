package com.example.sports.domain.dto;

public record AuthResponse(
        String token,
        long expiresIn,
        String message
) {
}
