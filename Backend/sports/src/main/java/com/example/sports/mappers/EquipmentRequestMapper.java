package com.example.sports.mappers;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.entities.EquipmentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EquipmentRequestMapper {
    EquipmentRequestMapper INSTANCE = Mappers.getMapper(EquipmentRequestMapper.class);

    EquipmentRequestDto toDto(EquipmentRequest equipmentRequest);
    EquipmentRequest fromDto(EquipmentRequestDto equipmentRequestDto);
}
