package com.example.sports.services.impl;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.entities.*;
import com.example.sports.mappers.EquipmentMapper;
import com.example.sports.repositories.EquipmentRepository;
import com.example.sports.services.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {

        // Null Checks
        if(equipmentDto.name() == null || equipmentDto.name().isBlank())
            throw new IllegalArgumentException("Equipment name must be specified");
        if(equipmentDto.availabilityStatus() == null)
            throw new IllegalArgumentException("Equipment Availability Status must be specified");
        if(equipmentDto.quantity() == null)
            throw new IllegalArgumentException("Equipment quantity must be specified");

        return EquipmentMapper.INSTANCE.toDto(
                equipmentRepository.save(new Equipment(
                        null,
                        equipmentDto.name(),
                        equipmentDto.category(),
                        equipmentDto.availabilityStatus(),
                        equipmentDto.quantity(),
                        equipmentDto.condition(),
                        null
                ))
        );
    }

    @Override
    public EquipmentDto updateEquipment(UUID equipmentId, EquipmentDto equipmentDto) {

        if(null == equipmentId)
            throw new IllegalArgumentException("Equipment must have an ID");

        Equipment existingEquipment = equipmentRepository.findById(equipmentId).orElseThrow(() ->
                new IllegalArgumentException("Equipment not found"));

        // Partial Update for availabilityStatus & quantity
        if(equipmentDto.availabilityStatus() != null)
            existingEquipment.setAvailabilityStatus(equipmentDto.availabilityStatus());
        if(equipmentDto.quantity() != null)
            existingEquipment.setQuantity(equipmentDto.quantity());

        // if(equipmentDto.condition() != null && !equipmentDto.condition().isBlank())
        //    existingEquipment.setCondition(equipmentDto.condition());

        return EquipmentMapper.INSTANCE.toDto(equipmentRepository.save(existingEquipment));
    }

    @Override
    public EquipmentDto getEquipment(UUID equipmentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment Id is Invalid"));

        return EquipmentMapper.INSTANCE.toDto(equipment);
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipment(UUID equipmentId) {
        equipmentRepository.deleteById(equipmentId);
    }
}
