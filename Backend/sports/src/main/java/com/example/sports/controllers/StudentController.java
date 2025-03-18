package com.example.sports.controllers;

import com.example.sports.domain.dto.InfrastructureRequestDto;
import com.example.sports.services.InfrastructureRequestService;
import com.example.sports.services.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;
    private final InfrastructureRequestService infrastructureRequestService;

    public StudentController(StudentService studentService, InfrastructureRequestService infrastructureRequestService) {
        this.studentService = studentService;
        this.infrastructureRequestService = infrastructureRequestService;
    }

//    // This is a test
//    @GetMapping(path = "/hello")
//    public String sayHello() {
//        return "Hello from Secure Endpoint";
//    }
//
//    @GetMapping("/me")
//    public UserDto getUser(@RequestAttribute UUID userId) {
//        return studentService.getUser(userId);
//    }

    @PostMapping("/infrastructure/{infrastructure_id}/infrastructure-request")
    public ResponseEntity<InfrastructureRequestDto> createInfrastructureRequest(
            @RequestBody InfrastructureRequestDto infrastructureRequestDto,
            @RequestAttribute UUID userId,
            @PathVariable("infrastructure_id") UUID infrastructureId
    ) {

        return new ResponseEntity<>(
                infrastructureRequestService.createInfrastructureRequest(
                        infrastructureRequestDto,
                        studentService.getUser(userId),
                        infrastructureId
                ), HttpStatus.CREATED
        );
    }
}
