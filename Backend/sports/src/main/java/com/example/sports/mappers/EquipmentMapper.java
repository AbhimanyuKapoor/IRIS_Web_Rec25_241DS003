package com.example.sports.mappers;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EquipmentMapper {
    EquipmentMapper INSTANCE = Mappers.getMapper(EquipmentMapper.class);

    EquipmentDto toDto(Equipment equipment);
    Equipment fromDto(EquipmentDto equipmentDto);
}
