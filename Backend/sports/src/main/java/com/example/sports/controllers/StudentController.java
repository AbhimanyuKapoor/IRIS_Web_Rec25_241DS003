package com.example.sports.controllers;

import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.services.EquipmentRequestService;
import com.example.sports.services.InfrastructureRequestService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*") // Enables CORS, Remove After Testing
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final InfrastructureRequestService infrastructureRequestService;
    private final EquipmentRequestService equipmentRequestService;

    public StudentController(InfrastructureRequestService infrastructureRequestService, EquipmentRequestService equipmentRequestService) {
        this.infrastructureRequestService = infrastructureRequestService;
        this.equipmentRequestService = equipmentRequestService;
    }

    @PostMapping("/infrastructure/{infrastructure_id}/infrastructure-request")
    public ResponseEntity<InfrastructureRequestDto> createInfrastructureRequest(
            @RequestBody InfrastructureRequestDto infrastructureRequestDto,
            @RequestAttribute UUID userId,
            @PathVariable("infrastructure_id") UUID infrastructureId
    ) {

        return new ResponseEntity<>(
                infrastructureRequestService.createInfrastructureRequest(
                        infrastructureRequestDto,
                        userId,
                        infrastructureId
                ), HttpStatus.CREATED
        );
    }

    @PostMapping("/equipment/{equipment_id}/equipment-request")
    public ResponseEntity<EquipmentRequestDto> createEquipmentRequest(
            @RequestBody EquipmentRequestDto equipmentRequestDto,
            @RequestAttribute UUID userId,
            @PathVariable("equipment_id") UUID equipmentId
    ) {

        return new ResponseEntity<>(
                equipmentRequestService.createEquipmentRequest(
                        equipmentRequestDto,
                        userId,
                        equipmentId
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/equipment-request")
    public List<EquipmentRequestDto> getEquipmentRequests(@RequestAttribute UUID userId) {
        return equipmentRequestService.getEquipmentRequestByUser(userId);
    }

    @GetMapping("/infrastructure-request")
    public List<InfrastructureRequestDto> getInfrastructureRequests(@RequestAttribute UUID userId) {
        return infrastructureRequestService.getInfrastructureRequestByUser(userId);
    }

    @PatchMapping("/infrastructure-request/{infrastructure_request_id}")
    public ResponseEntity<InfrastructureRequestDto> cancelInfrastructureRequest(
            @PathVariable("infrastructure_request_id") UUID infrastructureRequestId,
            @RequestBody InfrastructureRequestDto infrastructureRequestDto
    ) {
        InfrastructureRequestDto updatedInfraRequestDto = infrastructureRequestService.updateInfrastructureRequest(
                infrastructureRequestId,
                infrastructureRequestDto,
                true
        );

        return new ResponseEntity<>(updatedInfraRequestDto, HttpStatus.OK);
    }
}
