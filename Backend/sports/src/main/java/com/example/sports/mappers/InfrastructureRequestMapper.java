package com.example.sports.mappers;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.InfrastructureRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfrastructureRequestMapper {
    InfrastructureRequestMapper INSTANCE = Mappers.getMapper(InfrastructureRequestMapper.class);

    @Mapping(target = "userId", source = "user.id")
    InfrastructureRequestDto toDto (InfrastructureRequest infrastructureRequest);

    @Mapping(target = "user", ignore = true)
    InfrastructureRequest fromDto(InfrastructureRequestDto infrastructureRequestDto);
}
