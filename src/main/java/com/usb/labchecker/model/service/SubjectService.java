package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.SubjectByStudentIdDto;
import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.repository.LabResultRepository;
import com.usb.labchecker.model.repository.StudentRepository;
import com.usb.labchecker.model.repository.SubjectRepository;
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

    public List<SubjectByStudentIdDto> getSubjectsByStudentId(Integer studentId) {
        List<SubjectByStudentIdDto> subjectList = new ArrayList<>();
        labResultRepository
                .findAllByStudent(studentRepository.getOne(studentId))
                .forEach(e -> {
                    SubjectByStudentIdDto subject = SubjectByStudentIdDto.builder()
                            .id(e.getLab().getCourse().getSubject().getId())
                            .teacher(e.getLab().getCourse().getTeacher().getFirstName() +
                                    e.getLab().getCourse().getTeacher().getLastName())
                            .name(e.getLab().getCourse().getSubject().getName())
                            .build();
                    subjectList.add(subject);
                });
        return subjectList;

    }
}

