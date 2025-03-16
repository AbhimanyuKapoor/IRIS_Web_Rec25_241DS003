package com.example.sports.domain.dto;

import com.example.sports.domain.entities.EquipmentRequest;
import com.example.sports.domain.entities.InfrastructureRequest;
import com.example.sports.domain.entities.Role;

import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String email,
        String password,
        String branch,
        Role role,
        List<EquipmentRequest> equipmentRequests,
        List<InfrastructureRequest> infrastructureRequests
) {
}
