package com.example.sports.controllers;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.services.EquipmentService;
import com.example.sports.services.InfrastructureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EquipmentService equipmentService;
    private final InfrastructureService infrastructureService;

    public AdminController(EquipmentService equipmentService, InfrastructureService infrastructureService) {
        this.equipmentService = equipmentService;
        this.infrastructureService = infrastructureService;
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
}
