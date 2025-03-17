package com.example.sports.controllers;

import com.example.sports.services.StudentService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // This is a test
    @GetMapping
    public String sayHello() {
        return "Hello from Secure Endpoint";
    }
}
