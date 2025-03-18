package com.example.sports.services.impl;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.dto.UserDto;
import com.example.sports.domain.entities.Infrastructure;
import com.example.sports.domain.entities.InfrastructureRequest;
import com.example.sports.domain.entities.RequestStatus;
import com.example.sports.domain.entities.User;
import com.example.sports.mappers.InfrastructureRequestMapper;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.InfrastructureRepository;
import com.example.sports.repositories.InfrastructureRequestRepository;
import com.example.sports.services.InfrastructureRequestService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InfrastructureRequestServiceImpl implements InfrastructureRequestService {

    private final InfrastructureRequestRepository infrastructureRequestRepository;
    private final InfrastructureRepository infrastructureRepository;

    public InfrastructureRequestServiceImpl(InfrastructureRequestRepository infrastructureRequestRepository, InfrastructureRepository infrastructureRepository) {
        this.infrastructureRequestRepository = infrastructureRequestRepository;
        this.infrastructureRepository = infrastructureRepository;
    }

    @Override
    public InfrastructureRequestDto createInfrastructureRequest(
            InfrastructureRequestDto infrastructureRequestDto,
            UserDto userDto,
            UUID infrastructureId
    ) {

        Infrastructure infrastructure = infrastructureRepository.findById(infrastructureId)
                .orElse(null);

        // orELse error: temporary testing, null

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);

        // Null Check

        return InfrastructureRequestMapper.INSTANCE.toDto(
                infrastructureRequestRepository.save(new InfrastructureRequest(
                        null,
                        infrastructureRequestDto.requestedFor(),
                        RequestStatus.PENDING_APPROVAL,
                        user,
                        infrastructure
                ))
        );
    }
}
