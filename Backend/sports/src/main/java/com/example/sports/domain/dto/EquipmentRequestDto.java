package com.example.sports.domain.dto;

import com.example.sports.domain.entities.Equipment;
import com.example.sports.domain.entities.RequestStatus;
import com.example.sports.domain.entities.User;

import java.util.UUID;

public record EquipmentRequestDto(
        UUID id,
        String comments,
        RequestStatus requestStatus,
        String instructions,
        User user,
        Equipment equipment
) {
}
