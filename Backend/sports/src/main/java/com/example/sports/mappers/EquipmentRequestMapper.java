package com.example.sports.mappers;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.entities.EquipmentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EquipmentRequestMapper {
    EquipmentRequestMapper INSTANCE = Mappers.getMapper(EquipmentRequestMapper.class);

    @Mapping(target = "userId", source = "user.id")
    EquipmentRequestDto toDto(EquipmentRequest equipmentRequest);

    @Mapping(target = "user", ignore = true)
    EquipmentRequest fromDto(EquipmentRequestDto equipmentRequestDto);
}
