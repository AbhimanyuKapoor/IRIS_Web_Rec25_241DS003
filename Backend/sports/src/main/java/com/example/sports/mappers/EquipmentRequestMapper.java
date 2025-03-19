package com.example.sports.mappers;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.entities.EquipmentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EquipmentMapper.class)
public interface EquipmentRequestMapper {
    EquipmentRequestMapper INSTANCE = Mappers.getMapper(EquipmentRequestMapper.class);

    @Mapping(target = "equipmentDto", source = "equipment")
    @Mapping(target = "userId", source = "user.id")
    EquipmentRequestDto toDto(EquipmentRequest equipmentRequest);

    @Mapping(target = "equipment", source = "equipmentDto")
    @Mapping(target = "user", ignore = true)
    EquipmentRequest fromDto(EquipmentRequestDto equipmentRequestDto);
}
