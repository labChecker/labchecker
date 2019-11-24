package com.usb.labchecker.controller;

import com.usb.labchecker.model.dto.LabResultByStudentIdDto;
import com.usb.labchecker.model.dto.LabResultDto;
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

    @PostMapping
    public LabResult addLabResult(@RequestBody LabResultDto labResultDto){
        return labResultService.addLabResult(labResultDto);
    }

    @GetMapping
    public Iterable<LabResultByStudentIdDto> getLabResultsByStudentId(@RequestParam(name = "studentId") Integer studentId) {
        return labResultService.findLabResultsForStudent(studentId);
    }

    @GetMapping("/by_student_and_subject")
    public Set<LabResultByStudentIdDto> getLabResultsByStudentIdAndSubjectId(@RequestParam(name = "studentId") Integer studentId,
                                                                             @RequestParam(name = "subjectId") Integer subjectId){
        return labResultService.getLabResultsByStudentIdAndSubjectId(studentId, subjectId);
    }
}
