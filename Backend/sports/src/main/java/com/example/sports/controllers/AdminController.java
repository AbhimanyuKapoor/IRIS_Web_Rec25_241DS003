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

//    // This is a test
//    @GetMapping(path = "/hello")
//    public String sayHello() {
//        return "Hello from Secure Endpoint";
//    }

    @PostMapping("/equipment")
    public ResponseEntity<EquipmentDto> addEquipment(@RequestBody EquipmentDto equipmentDto) {
        return new ResponseEntity<>(equipmentService.addEquipment(equipmentDto), HttpStatus.CREATED);
    }

    @PostMapping("/infrastructure")
    public ResponseEntity<InfrastructureDto> addInfrastructure(@RequestBody InfrastructureDto infrastructureDto) {
        return new ResponseEntity<>(infrastructureService.addInfrastructure(infrastructureDto), HttpStatus.CREATED);
    }
}
