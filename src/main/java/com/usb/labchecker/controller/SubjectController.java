package com.usb.labchecker.controller;

import com.usb.labchecker.model.dto.SubjectByStudentIdDto;
import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable("id") Integer subjectId){
        return subjectService.getOne(subjectId);
    }

    @GetMapping
    public List<SubjectByStudentIdDto> getSubjectsByStudentId(@RequestParam(name = "studentId") Integer studentId){
        return subjectService.getSubjectsByStudentId(studentId);
    }
}
