package com.usb.labchecker.controller;

import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.service.LabResultService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/labresults")
public class LabResultController {
    private final LabResultService labResultService;

    public LabResultController(LabResultService labResultService) {
        this.labResultService = labResultService;
    }

    @GetMapping
    Iterable<LabResult> getAllLabResultsForStudent(@RequestParam(name = "studentId") Integer studentId) {
        return labResultService.findLabResultsForStudent(studentId);
    }

    @GetMapping
    public Set<LabResult> getLabResultsByStudentIdAndSubjectId(@RequestParam(name = "studentId") Integer studentId,
                                                               @RequestParam(name = "subjectId") Integer subjectId){
        return labResultService.getLabResultsByStudentIdAndSubjectId(studentId, subjectId);
    }
}
