package com.example.sports.services;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.dto.InfrastructureRequestDto;

import java.util.UUID;

public interface EquipmentRequestService {

    EquipmentRequestDto createEquipmentRequest(EquipmentRequestDto equipmentRequestDto, UUID userId, UUID equipmentId);
}
