package com.example.sports.services;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.dto.UserDto;

import java.util.UUID;

public interface InfrastructureRequestService {

    InfrastructureRequestDto createInfrastructureRequest(InfrastructureRequestDto infrastructureRequestDto, UserDto userDto, UUID infrastructureId);
}
