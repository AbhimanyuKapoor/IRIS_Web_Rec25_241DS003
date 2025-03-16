package com.example.sports.domain.dto;

import com.example.sports.domain.entities.AvailabilityStatus;

import java.util.UUID;

public record EquipmentDto(
        UUID id,
        String name,
        String category,
        AvailabilityStatus availabilityStatus,
        Integer quantity,
        String condition
) {
}
