package com.example.sports.services.impl;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.entities.*;
import com.example.sports.mappers.EquipmentRequestMapper;
import com.example.sports.mappers.UserMapper;
import com.example.sports.repositories.EquipmentRepository;
import com.example.sports.repositories.EquipmentRequestRepository;
import com.example.sports.services.EquipmentRequestService;
import com.example.sports.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentRequestServiceImpl implements EquipmentRequestService {

    private final StudentService studentService;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentRequestRepository equipmentRequestRepository;

    public EquipmentRequestServiceImpl(StudentService studentService, EquipmentRepository equipmentRepository, EquipmentRequestRepository equipmentRequestRepository) {
        this.studentService = studentService;
        this.equipmentRepository = equipmentRepository;
        this.equipmentRequestRepository = equipmentRequestRepository;
    }

    @Override
    public EquipmentRequestDto createEquipmentRequest(
            EquipmentRequestDto equipmentRequestDto,
            UUID userId,
            UUID equipmentId
    ) {

        User user = UserMapper.INSTANCE.userDtoToUser(studentService.getUser(userId));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment Does Not Exist"));

        // Null Check

        return EquipmentRequestMapper.INSTANCE.toDto(
                equipmentRequestRepository.save(new EquipmentRequest(
                        null,
                        RequestStatus.PENDING_APPROVAL,
                        null,
                        null,
                        equipmentRequestDto.quantity(),
                        equipmentRequestDto.duration(),
                        user,
                        equipment
                ))
        );
    }
}
