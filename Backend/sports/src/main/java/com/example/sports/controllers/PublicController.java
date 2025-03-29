package com.example.sports.controllers;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.services.EquipmentService;
import com.example.sports.services.InfrastructureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*") // Enables CORS, Remove After Testing
@RestController
@RequestMapping(path = "/public")
public class PublicController {

    private final EquipmentService equipmentService;
    private final InfrastructureService infrastructureService;

    public PublicController(EquipmentService equipmentService, InfrastructureService infrastructureService) {
        this.infrastructureService = infrastructureService;
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipment")
    public List<EquipmentDto> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/infrastructure")
    public List<InfrastructureDto> getAllInfrastructure() {
        return infrastructureService.getAllInfrastructure();
    }

    @GetMapping("equipment/{equipment_id}")
    public ResponseEntity<EquipmentDto> getEquipment(@PathVariable("equipment_id") UUID equipmentId) {
        return ResponseEntity.ok(equipmentService.getEquipment(equipmentId));
    }

    @GetMapping("infrastructure/{infrastructure_id}")
    public ResponseEntity<InfrastructureDto> getInfrastructure(@PathVariable("infrastructure_id") UUID infrastructureId) {
        return ResponseEntity.ok(infrastructureService.getInfrastructure(infrastructureId));
    }
}
