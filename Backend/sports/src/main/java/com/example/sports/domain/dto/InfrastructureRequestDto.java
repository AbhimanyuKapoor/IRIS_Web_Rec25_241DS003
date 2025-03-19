package com.example.sports.domain.dto;

import com.example.sports.domain.entities.RequestStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record InfrastructureRequestDto(
        UUID id,
        LocalTime requestedFor,
        LocalDate requestedOn,
        RequestStatus requestStatus,
        UUID userId,
        InfrastructureDto infrastructureDto
) {
}
