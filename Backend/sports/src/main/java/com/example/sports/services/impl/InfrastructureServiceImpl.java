package com.example.sports.services.impl;

import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.domain.entities.Infrastructure;
import com.example.sports.mappers.InfrastructureMapper;
import com.example.sports.repositories.InfrastructureRepository;
import com.example.sports.services.InfrastructureService;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureServiceImpl implements InfrastructureService {

    private final InfrastructureRepository infrastructureRepository;

    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    @Override
    public InfrastructureDto addInfrastructure(InfrastructureDto infrastructureDto) {

        return InfrastructureMapper.INSTANCE.toDto(
                infrastructureRepository.save(new Infrastructure(
                        null,
                        infrastructureDto.name(),
                        infrastructureDto.location(),
                        infrastructureDto.availabilityStatus(),
                        infrastructureDto.capacity(),
                        infrastructureDto.quantity(),
                        infrastructureDto.openingTime(),
                        infrastructureDto.closingTime(),
                        null
                ))
        );
    }
}
