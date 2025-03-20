package com.example.sports.services;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface InfrastructureRequestService {

    InfrastructureRequestDto createInfrastructureRequest(InfrastructureRequestDto infrastructureRequestDto, UUID userId, UUID infrastructureId);

    InfrastructureRequestDto updateInfrastructureRequest(UUID infrastructureRequestId, InfrastructureRequestDto infrastructureRequestDto, boolean calledByAdmin);
}