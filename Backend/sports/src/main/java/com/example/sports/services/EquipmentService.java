package com.example.sports.services;

import com.example.sports.domain.dto.EquipmentDto;

import java.util.List;
import java.util.UUID;

public interface EquipmentService {

    EquipmentDto addEquipment(EquipmentDto equipmentDto);

    EquipmentDto updateEquipment(UUID equipmentId, EquipmentDto equipmentDto);

    List<EquipmentDto> getAllEquipment();

    void deleteEquipment(UUID equipmentId);
}
