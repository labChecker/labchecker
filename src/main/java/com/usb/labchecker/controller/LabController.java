package com.usb.labchecker.controller;

import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.service.LabService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labs")
public class LabController {
    private final LabService labService;

    public LabController(LabService labService) {
        this.labService = labService;
    }

    @GetMapping("student/{id}")
    public Iterable<Lab> getLabsForStudent(@PathVariable("id") int telegramId) {
        return labService.getAllLabsForTelegramId(telegramId);
    }

    @GetMapping("/{labId}")
    public Lab getLabById(@PathVariable("labId") Integer labId){
        return labService.getOne(labId);
    }

    @GetMapping
    public List<Lab> getLabListByStudentId(@RequestParam(name = "studentId") Integer studentId){
        return labService.getLabListByStudentId(studentId);
    }

}
