package com.example.sports.services.impl;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.entities.*;
import com.example.sports.mappers.EquipmentRequestMapper;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.EquipmentRepository;
import com.example.sports.repositories.EquipmentRequestRepository;
import com.example.sports.services.EquipmentRequestService;
import com.example.sports.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentRequestServiceImpl implements EquipmentRequestService {

    private final UserService userService;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentRequestRepository equipmentRequestRepository;

    public EquipmentRequestServiceImpl(UserService userService, EquipmentRepository equipmentRepository, EquipmentRequestRepository equipmentRequestRepository) {
        this.userService = userService;
        this.equipmentRepository = equipmentRepository;
        this.equipmentRequestRepository = equipmentRequestRepository;
    }

    @Override
    public EquipmentRequestDto createEquipmentRequest(
            EquipmentRequestDto equipmentRequestDto,
            UUID userId,
            UUID equipmentId
    ) {

        User user = UserMapper.INSTANCE.userDtoToUser(userService.getUser(userId));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment Does Not Exist"));

        if(equipmentRequestDto.quantity() > equipment.getQuantity())
            throw new IllegalArgumentException("Cannot Request for more than Available");

        if(equipment.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE)
            throw new IllegalArgumentException("Equipment is currently Unavailable");

        return EquipmentRequestMapper.INSTANCE.toDto(
                equipmentRequestRepository.save(new EquipmentRequest(
                        null,
                        RequestStatus.PENDING,
                        null,
                        null,
                        equipmentRequestDto.quantity(),
                        equipmentRequestDto.duration(),
                        user,
                        equipment
                ))
        );
    }

    @Override
    public EquipmentRequestDto updateEquipmentRequest(UUID equipmentRequestId, EquipmentRequestDto equipmentRequestDto, boolean calledByAdmin) {

        if(null == equipmentRequestId)
            throw new IllegalArgumentException("Equipment Request must have an ID");

        EquipmentRequest existingEquipmentRequest = equipmentRequestRepository.findById(equipmentRequestId).orElseThrow(() ->
                new IllegalArgumentException("Equipment Request not found"));

        // Partial Update for requestStatus, comments & instructions
        if(equipmentRequestDto.requestStatus() != null)
            existingEquipmentRequest.setRequestStatus(equipmentRequestDto.requestStatus());
        if(equipmentRequestDto.comments() != null && !equipmentRequestDto.comments().isBlank())
            existingEquipmentRequest.setComments(equipmentRequestDto.comments());
        if(equipmentRequestDto.instructions() != null && !equipmentRequestDto.instructions().isBlank())
            existingEquipmentRequest.setInstructions(equipmentRequestDto.instructions());

        if(calledByAdmin) {
            if(existingEquipmentRequest.getRequestStatus() == RequestStatus.APPROVED) {

                Equipment existingEquipment = getEquipment(existingEquipmentRequest);
                equipmentRepository.save(existingEquipment);
            }
        }

        return EquipmentRequestMapper.INSTANCE.toDto(equipmentRequestRepository.save(existingEquipmentRequest));
    }

    private static Equipment getEquipment(EquipmentRequest existingEquipmentRequest) {
        Equipment existingEquipment = existingEquipmentRequest.getEquipment();

        existingEquipment.setQuantity(existingEquipment.getQuantity() - existingEquipmentRequest.getQuantity());

        if(existingEquipment.getQuantity() < 0)
            throw new IllegalArgumentException("Cannot approve more Equipment than Available");
        if(existingEquipment.getQuantity() == 0)
            existingEquipment.setAvailabilityStatus(AvailabilityStatus.UNAVAILABLE);

        return existingEquipment;
    }
}
