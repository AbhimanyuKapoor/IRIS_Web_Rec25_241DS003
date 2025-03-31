package com.example.sports.mappers;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.InfrastructureRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = InfrastructureMapper.class)
public interface InfrastructureRequestMapper {
    InfrastructureRequestMapper INSTANCE = Mappers.getMapper(InfrastructureRequestMapper.class);

    @Mapping(target = "infrastructureDto", source = "infrastructure")
    @Mapping(target = "userId", source = "user.id")
    InfrastructureRequestDto toDto (InfrastructureRequest infrastructureRequest);

    @Mapping(target = "infrastructure", source = "infrastructureDto")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updated", ignore = true)
    InfrastructureRequest fromDto(InfrastructureRequestDto infrastructureRequestDto);
}
