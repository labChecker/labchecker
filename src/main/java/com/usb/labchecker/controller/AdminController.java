package com.usb.labchecker.controller;

import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.entity.Student;
import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.service.LabResultService;
import com.usb.labchecker.model.service.LabService;
import com.usb.labchecker.model.service.StudentService;
import com.usb.labchecker.model.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final LabService labService;
    private final LabResultService labResultService;

    public AdminController(StudentService studentService,
                           SubjectService subjectService,
                           LabService labService,
                           LabResultService labResultService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.labService = labService;
        this.labResultService = labResultService;
    }

    @GetMapping("students")
    public Iterable<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("subjects")
    public Iterable<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("labs")
    public Iterable<Lab> getLabs() {
        return labService.getAllLabs();
    }

    @GetMapping("labresults")
    public Iterable<LabResult> getLabResults() {
        return labResultService.getAllLabResults();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getOne(id);
    }
}
