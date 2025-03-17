package com.example.sports.mappers;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.InfrastructureRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfrastructureRequestMapper {
    InfrastructureRequestMapper INSTANCE = Mappers.getMapper(InfrastructureRequestMapper.class);

    InfrastructureRequestDto toDto (InfrastructureRequest infrastructureRequest);
    InfrastructureRequest fromDto(InfrastructureRequestDto infrastructureRequestDto);
}
