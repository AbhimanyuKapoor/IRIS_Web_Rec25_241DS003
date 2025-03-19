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
import com.example.sports.services.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InfrastructureRequestServiceImpl implements InfrastructureRequestService {

    private final InfrastructureRequestRepository infrastructureRequestRepository;
    private final InfrastructureRepository infrastructureRepository;
    private final StudentService studentService;

    public InfrastructureRequestServiceImpl(InfrastructureRequestRepository infrastructureRequestRepository, InfrastructureRepository infrastructureRepository, StudentService studentService) {
        this.infrastructureRequestRepository = infrastructureRequestRepository;
        this.infrastructureRepository = infrastructureRepository;
        this.studentService = studentService;
    }

    @Override
    public InfrastructureRequestDto createInfrastructureRequest(
            InfrastructureRequestDto infrastructureRequestDto,
            UUID userId,
            UUID infrastructureId
    ) {

        User user = UserMapper.INSTANCE.userDtoToUser(studentService.getUser(userId));
        Infrastructure infrastructure = infrastructureRepository.findById(infrastructureId)
                .orElseThrow(() -> new IllegalArgumentException("Infrastructure Does Not Exist"));

        // Null Check

        return InfrastructureRequestMapper.INSTANCE.toDto(
                infrastructureRequestRepository.save(new InfrastructureRequest(
                        null,
                        infrastructureRequestDto.requestedFor(),
                        LocalDate.now(),
                        RequestStatus.PENDING_APPROVAL,
                        user,
                        infrastructure
                ))
        );
    }
}
