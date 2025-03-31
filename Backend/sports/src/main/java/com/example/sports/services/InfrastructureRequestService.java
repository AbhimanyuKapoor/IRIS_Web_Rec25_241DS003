package com.example.sports.services;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.dto.UserDto;
import com.example.sports.domain.entities.InfrastructureRequest;
import com.example.sports.domain.entities.RequestStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface InfrastructureRequestService {

    InfrastructureRequestDto createInfrastructureRequest(InfrastructureRequestDto infrastructureRequestDto, UUID userId, UUID infrastructureId);

    InfrastructureRequestDto updateInfrastructureRequest(UUID infrastructureRequestId, InfrastructureRequestDto infrastructureRequestDto, boolean calledByUser);

    List<InfrastructureRequestDto> getInfrastructureRequestByUser(UUID userId);

    List<InfrastructureRequestDto> getInfrastructureRequestByDateAndRequestStatus(LocalDate date, RequestStatus requestStatus);
}