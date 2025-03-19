package com.example.sports.services;

import com.example.sports.domain.dto.InfrastructureDto;

import java.util.List;
import java.util.UUID;

public interface InfrastructureService {

    InfrastructureDto addInfrastructure(InfrastructureDto infrastructureDto);

    InfrastructureDto updateInfrastructure(UUID infrastructureId, InfrastructureDto infrastructureDto);

    List<InfrastructureDto> getAllInfrastructure();

    void deleteInfrastructure(UUID infrastructureId);
}
