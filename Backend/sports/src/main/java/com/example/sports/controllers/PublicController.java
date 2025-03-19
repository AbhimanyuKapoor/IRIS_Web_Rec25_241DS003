package com.example.sports.controllers;

import com.example.sports.domain.dto.EquipmentDto;
import com.example.sports.domain.dto.InfrastructureDto;
import com.example.sports.services.EquipmentService;
import com.example.sports.services.InfrastructureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
