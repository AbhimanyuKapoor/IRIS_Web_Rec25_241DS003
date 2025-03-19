package com.example.sports.mappers;

import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.domain.entities.Infrastructure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InfrastructureMapper {
    InfrastructureMapper INSTANCE = Mappers.getMapper(InfrastructureMapper.class);

    InfrastructureDto toDto (Infrastructure infrastructure);

    @Mapping(target = "infrastructureRequests", ignore = true)
    Infrastructure fromDto(InfrastructureDto infrastructureDto);
}
