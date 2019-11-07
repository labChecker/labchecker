package com.usb.labchecker.controller;

import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.service.LabService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
