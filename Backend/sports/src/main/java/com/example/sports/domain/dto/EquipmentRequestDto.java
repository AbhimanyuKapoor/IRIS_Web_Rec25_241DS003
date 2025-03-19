package com.example.sports.domain.dto;

import com.example.sports.domain.entities.RequestStatus;

import java.util.UUID;

public record EquipmentRequestDto(
        UUID id,
        String comments,
        RequestStatus requestStatus,
        String instructions,
        Integer quantity,
        String duration,
        UUID userId,
        EquipmentDto equipmentDto
) {
}
