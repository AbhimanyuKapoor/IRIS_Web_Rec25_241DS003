package com.example.sports.services.impl;

import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.domain.entities.Infrastructure;
import com.example.sports.mappers.InfrastructureMapper;
import com.example.sports.repositories.InfrastructureRepository;
import com.example.sports.services.InfrastructureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InfrastructureServiceImpl implements InfrastructureService {

    private final InfrastructureRepository infrastructureRepository;

    public InfrastructureServiceImpl(InfrastructureRepository infrastructureRepository) {
        this.infrastructureRepository = infrastructureRepository;
    }

    @Override
    public InfrastructureDto addInfrastructure(InfrastructureDto infrastructureDto) {

        // Null Checks
        if(infrastructureDto.name() == null || infrastructureDto.name().isBlank())
            throw new IllegalArgumentException("Infrastructure name must be specified");
        if(infrastructureDto.availabilityStatus() == null)
            throw new IllegalArgumentException("Infrastructure Availability Status must be specified");
        if(infrastructureDto.quantity() == null)
            throw new IllegalArgumentException("Infrastructure quantity must be specified");
        if(infrastructureDto.openingTime() == null)
            throw new IllegalArgumentException("Infrastructure opening time must be specified");
        if(infrastructureDto.closingTime() == null)
            throw new IllegalArgumentException("Infrastructure closing time must be specified");

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

    @Override
    public InfrastructureDto updateInfrastructure(UUID infrastructureId, InfrastructureDto infrastructureDto) {

        if(null == infrastructureId)
            throw new IllegalArgumentException("Infrastructure must have an ID");

        Infrastructure existingInfrastructure = infrastructureRepository.findById(infrastructureId).orElseThrow(() ->
                new IllegalArgumentException("Infrastructure not found"));

        // Partial Update for availabilityStatus & quantity
        if(infrastructureDto.availabilityStatus() != null)
            existingInfrastructure.setAvailabilityStatus(infrastructureDto.availabilityStatus());
        if(infrastructureDto.quantity() != null)
            existingInfrastructure.setQuantity(infrastructureDto.quantity());

        return InfrastructureMapper.INSTANCE.toDto(infrastructureRepository.save(existingInfrastructure));
    }

    @Override
    public List<InfrastructureDto> getAllInfrastructure() {
        return infrastructureRepository.findAll()
                .stream()
                .map(InfrastructureMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InfrastructureDto getInfrastructure(UUID infrastructureId) {
        Infrastructure infrastructure = infrastructureRepository.findById(infrastructureId)
                .orElseThrow(() -> new IllegalArgumentException("Infrastructure Id is Invalid"));

        return InfrastructureMapper.INSTANCE.toDto(infrastructure);
    }

    @Override
    public void deleteInfrastructure(UUID infrastructureId) {
        infrastructureRepository.deleteById(infrastructureId);
    }
}
