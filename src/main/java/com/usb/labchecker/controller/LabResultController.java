package com.usb.labchecker.controller;

import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.service.LabResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/labresults")
public class LabResultController {
    private final LabResultService labResultService;

    public LabResultController(LabResultService labResultService) {
        this.labResultService = labResultService;
    }

    @GetMapping("student/{id}")
    Iterable<LabResult> getAllLabResultsForStudent(@PathVariable("id") int telegramId) {
        return labResultService.findLabResultsForStudent(telegramId);
    }
}
