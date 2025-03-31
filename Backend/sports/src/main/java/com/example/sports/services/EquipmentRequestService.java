package com.example.sports.services;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.RequestStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EquipmentRequestService {

    EquipmentRequestDto createEquipmentRequest(EquipmentRequestDto equipmentRequestDto, UUID userId, UUID equipmentId);

    EquipmentRequestDto updateEquipmentRequest(UUID equipmentRequestId, EquipmentRequestDto equipmentRequestDto, boolean calledByAdmin);

    List<EquipmentRequestDto> getEquipmentRequestByUser(UUID userId);

    List<EquipmentRequestDto> getEquipmentRequestByRequestStatus(RequestStatus requestStatus);
}
