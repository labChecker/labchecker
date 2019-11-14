package com.usb.labchecker.controller;

import com.usb.labchecker.model.dto.StudentDto;
import com.usb.labchecker.model.entity.Student;
import com.usb.labchecker.model.service.StudentService;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    ResponseEntity<Object> createStudent(@RequestBody StudentDto studentDto) {
        Student newStudent = studentService.createStudent(studentDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/telegramId/{id}")
    public Integer getStudentIdByTelegramId(@PathVariable("id") Integer telegramId){
        return studentService.getStudentIdByTelegramId(telegramId);
    }


}
