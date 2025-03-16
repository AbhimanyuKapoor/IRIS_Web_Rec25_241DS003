package com.example.sports.domain.dto;

import com.example.sports.domain.entities.AvailabilityStatus;

import java.util.UUID;

public record InfrastructureDto(
        UUID id,
        String name,
        String location,
        AvailabilityStatus availabilityStatus,
        Integer quantity,
        Integer capacity,
        String operatingHrs
) {
}
