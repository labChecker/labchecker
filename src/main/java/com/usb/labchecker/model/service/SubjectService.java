package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final LabResultRepository labResultRepository;

    public SubjectService(SubjectRepository subjectRepository,
                          StudentRepository studentRepository,
                          LabResultRepository labResultRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.labResultRepository = labResultRepository;

    }

    public Subject getOne(int id) {
        return subjectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByStudentId(Integer studentId) {
        List<Subject> subjectList = new ArrayList<>();
        labResultRepository
                .findAllByStudent(studentRepository.getOne(studentId))
                .forEach(e -> subjectList.add(e.getLab().getCourse().getSubject()));
        return subjectList;

    }
}
