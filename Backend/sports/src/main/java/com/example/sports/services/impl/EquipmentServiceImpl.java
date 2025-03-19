package com.example.sports.services.impl;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.entities.*;
import com.example.sports.mappers.EquipmentMapper;
import com.example.sports.mappers.InfrastructureRequestMapper;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.EquipmentRepository;
import com.example.sports.services.EquipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public EquipmentDto addEquipment(EquipmentDto equipmentDto) {

        // Null Check

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
}
