package com.example.sports.domain.dto;

import com.example.sports.domain.entities.Infrastructure;
import com.example.sports.domain.entities.RequestStatus;
import com.example.sports.domain.entities.User;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record InfrastructureRequestDto(
        UUID id,
        LocalTime requestedFor,
        RequestStatus requestStatus,
        UUID userId,
        Infrastructure infrastructure
) {
}
