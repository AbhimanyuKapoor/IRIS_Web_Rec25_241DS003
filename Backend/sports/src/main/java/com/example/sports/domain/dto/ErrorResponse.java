package com.example.sports.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
