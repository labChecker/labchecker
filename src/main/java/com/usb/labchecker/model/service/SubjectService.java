package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getOne(int id) {
        return subjectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
