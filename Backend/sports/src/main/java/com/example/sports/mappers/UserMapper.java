package com.example.sports.mappers;

import com.example.sports.domain.dto.UserDto;
import com.example.sports.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

// To Map Nested Objects in a List, need to specify which Mapper to be used by Mapstruct for the objects in that List.
@Mapper(uses = {EquipmentRequestMapper.class, InfrastructureRequestMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "infrastructureRequestDtos", source = "infrastructureRequests")
    @Mapping(target = "equipmentRequestDtos", source = "equipmentRequests")
    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user);

    @Mapping(target = "infrastructureRequests", source = "infrastructureRequestDtos")
    @Mapping(target = "equipmentRequests", source = "equipmentRequestDtos")
    User userDtoToUser(UserDto userDto);
}
