package com.example.sports.controllers;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.dto.EquipmentRequestDto;
import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.domain.entities.RequestStatus;
import com.example.sports.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EquipmentService equipmentService;
    private final InfrastructureService infrastructureService;
    private final EquipmentRequestService equipmentRequestService;
    private final InfrastructureRequestService infrastructureRequestService;
    private final NotificationService notificationService;
    private final StudentService studentService;

    public AdminController(EquipmentService equipmentService, InfrastructureService infrastructureService, EquipmentRequestService equipmentRequestService, InfrastructureRequestService infrastructureRequestService, NotificationService notificationService, StudentService studentService) {
        this.equipmentService = equipmentService;
        this.infrastructureService = infrastructureService;
        this.equipmentRequestService = equipmentRequestService;
        this.infrastructureRequestService = infrastructureRequestService;
        this.notificationService = notificationService;
        this.studentService = studentService;
    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> addEquipment(@RequestBody EquipmentDto equipmentDto) {
        return new ResponseEntity<>(equipmentService.addEquipment(equipmentDto), HttpStatus.CREATED);
    }

    @PostMapping("/infrastructure")
    public ResponseEntity<InfrastructureDto> addInfrastructure(@RequestBody InfrastructureDto infrastructureDto) {
        return new ResponseEntity<>(infrastructureService.addInfrastructure(infrastructureDto), HttpStatus.CREATED);
    }

    @PatchMapping("/equipment/{equipment_id}")
    public ResponseEntity<EquipmentDto> updateEquipment(
            @PathVariable("equipment_id") UUID equipmentId,
            @RequestBody EquipmentDto equipmentDto
    ) {
        return new ResponseEntity<>(equipmentService.updateEquipment(equipmentId, equipmentDto), HttpStatus.OK);
    }

    @PatchMapping("/infrastructure/{infrastructure_id}")
    public ResponseEntity<InfrastructureDto> updateInfrastructure(
            @PathVariable("infrastructure_id") UUID infrastructureId,
            @RequestBody InfrastructureDto infrastructureDto
    ) {
        return new ResponseEntity<>(infrastructureService.updateInfrastructure(infrastructureId, infrastructureDto), HttpStatus.OK);
    }

    @DeleteMapping("/equipment/{equipment_id}")
    public void deleteEquipment(@PathVariable("equipment_id") UUID equipmentId) {
        equipmentService.deleteEquipment(equipmentId);
    }

    @DeleteMapping("/infrastructure/{infrastructure_id}")
    public void deleteInfrastructure(@PathVariable("infrastructure_id") UUID infrastructureId) {
        infrastructureService.deleteInfrastructure(infrastructureId);
    }

    @PatchMapping("/equipment-request/{equipment_request_id}")
    public ResponseEntity<EquipmentRequestDto> updateEquipmentRequest(
            @PathVariable("equipment_request_id") UUID equipmentRequestId,
            @RequestBody EquipmentRequestDto equipmentRequestDto
    ) {
        EquipmentRequestDto updatedEquipmentRequestDto = equipmentRequestService.updateEquipmentRequest(
                equipmentRequestId,
                equipmentRequestDto,
                true
        );

        String userEmail = studentService.getUser(updatedEquipmentRequestDto.userId()).email();

        if(updatedEquipmentRequestDto.requestStatus() == RequestStatus.APPROVED) {
            notificationService.sendNotification(
                    userEmail,
                    "Your Request for Equipment has been Approved!",
                    "Your Request for: "+updatedEquipmentRequestDto.equipmentDto().name()+"\nHas been Approved!"+"\nComments: "+updatedEquipmentRequestDto.comments()+"\nInstructions: "+updatedEquipmentRequestDto.instructions()
            );
        } else if(updatedEquipmentRequestDto.requestStatus() == RequestStatus.REJECTED) {
            notificationService.sendNotification(
                    userEmail,
                    "Your Request for Equipment has been Rejected",
                    "Your Request for: "+updatedEquipmentRequestDto.equipmentDto().name()+"\nHas been Rejected!"+"\nComments: "+updatedEquipmentRequestDto.comments()+"\nInstructions: "+updatedEquipmentRequestDto.instructions()
            );
        }

        return new ResponseEntity<>(updatedEquipmentRequestDto, HttpStatus.OK);
    }

    @PatchMapping("/infrastructure-request/{infrastructure_request_id}")
    public ResponseEntity<InfrastructureRequestDto> updateInfrastructureRequest(
            @PathVariable("infrastructure_request_id") UUID infrastructureRequestId,
            @RequestBody InfrastructureRequestDto infrastructureRequestDto
    ) {
        InfrastructureRequestDto updatedInfraRequestDto = infrastructureRequestService.updateInfrastructureRequest(
                infrastructureRequestId,
                infrastructureRequestDto,
                true
        );

        String userEmail = studentService.getUser(updatedInfraRequestDto.userId()).email();

        if (updatedInfraRequestDto.requestStatus() == RequestStatus.APPROVED) {
            notificationService.loadUpcomingBookingsIntoCache();
            notificationService.sendNotification(
                    userEmail,
                    "Your Booking Request has been Approved!",
                    "Your Request for: " + updatedInfraRequestDto.infrastructureDto().name() + "\nOn: " + updatedInfraRequestDto.requestedFor() + "\nHas been Approved!"
            );
        } else if (updatedInfraRequestDto.requestStatus() == RequestStatus.REJECTED) {
            notificationService.sendNotification(
                    userEmail,
                    "Your Booking Request has been Rejected",
                    "Your Request for: " + updatedInfraRequestDto.infrastructureDto().name() + "\nOn: " + updatedInfraRequestDto.requestedFor() + "\nHas been Rejected!"
            );
        }

        return new ResponseEntity<>(updatedInfraRequestDto, HttpStatus.OK);
    }
}
