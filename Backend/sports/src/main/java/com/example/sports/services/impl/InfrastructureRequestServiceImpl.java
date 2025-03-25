package com.example.sports.services.impl;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.*;
import com.example.sports.mappers.InfrastructureRequestMapper;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.InfrastructureRepository;
import com.example.sports.repositories.InfrastructureRequestRepository;
import com.example.sports.services.InfrastructureRequestService;
import com.example.sports.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InfrastructureRequestServiceImpl implements InfrastructureRequestService {

    private final InfrastructureRequestRepository infrastructureRequestRepository;
    private final InfrastructureRepository infrastructureRepository;
    private final UserService userService;

    public InfrastructureRequestServiceImpl(InfrastructureRequestRepository infrastructureRequestRepository, InfrastructureRepository infrastructureRepository, UserService userService) {
        this.infrastructureRequestRepository = infrastructureRequestRepository;
        this.infrastructureRepository = infrastructureRepository;
        this.userService = userService;
    }

    @Override
    public InfrastructureRequestDto createInfrastructureRequest(
            InfrastructureRequestDto infrastructureRequestDto,
            UUID userId,
            UUID infrastructureId
    ) {

        User user = UserMapper.INSTANCE.userDtoToUser(userService.getUser(userId));
        Infrastructure infrastructure = infrastructureRepository.findById(infrastructureId)
                .orElseThrow(() -> new IllegalArgumentException("Infrastructure Does Not Exist"));

        if(infrastructureRequestRepository.findByInfrastructureIdAndUserIdAndRequestedOn(infrastructureId, userId, LocalDate.now()).isPresent())
            throw new IllegalArgumentException("Only one slot per day per student is Allowed");

        if(infrastructure.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE)
            throw new IllegalArgumentException("Booking is currently Unavailable");

        return InfrastructureRequestMapper.INSTANCE.toDto(
                infrastructureRequestRepository.save(new InfrastructureRequest(
                        null,
                        infrastructureRequestDto.requestedFor(),
                        LocalDate.now(),
                        RequestStatus.PENDING,
                        false,
                        user,
                        infrastructure
                ))
        );
    }

    @Override
    public InfrastructureRequestDto updateInfrastructureRequest(UUID infrastructureRequestId, InfrastructureRequestDto infrastructureRequestDto, boolean calledByAdmin) {

        if(null == infrastructureRequestId)
            throw new IllegalArgumentException("Infrastructure Request must have an ID");

        InfrastructureRequest existingInfrastructureRequest = infrastructureRequestRepository.findById(infrastructureRequestId).orElseThrow(() ->
                new IllegalArgumentException("Infrastructure Request not found"));

        // Partial Update for requestStatus
        if(infrastructureRequestDto.requestStatus() != null)
            existingInfrastructureRequest.setRequestStatus(infrastructureRequestDto.requestStatus());

        if(calledByAdmin) {
            if(existingInfrastructureRequest.getRequestStatus() == RequestStatus.APPROVED) {

                Infrastructure existingInfrastructure = getInfrastructure(existingInfrastructureRequest);
                infrastructureRepository.save(existingInfrastructure);
            }
        }

        if(!calledByAdmin) {
            if (infrastructureRequestDto.reminderSent() != null)
                existingInfrastructureRequest.setReminderSent(infrastructureRequestDto.reminderSent());
        }

        return InfrastructureRequestMapper.INSTANCE.toDto(infrastructureRequestRepository.save(existingInfrastructureRequest));
    }

    private static Infrastructure getInfrastructure(InfrastructureRequest existingInfrastructureRequest) {
        Infrastructure existingInfrastructure = existingInfrastructureRequest.getInfrastructure();

        existingInfrastructure.setQuantity(existingInfrastructure.getQuantity() - 1);

        if(existingInfrastructure.getQuantity() < 0)
            throw new IllegalArgumentException("Cannot approve more Bookings than Available");
        if(existingInfrastructure.getQuantity() == 0)
            existingInfrastructure.setAvailabilityStatus(AvailabilityStatus.UNAVAILABLE);

        return existingInfrastructure;
    }
}
