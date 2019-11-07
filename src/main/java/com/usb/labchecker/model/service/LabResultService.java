package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.repository.LabResultRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LabResultService {
    private final LabResultRepository labResultRepository;
    private final StudentService studentService;

    public LabResultService(LabResultRepository labResultRepository, StudentService studentService) {
        this.labResultRepository = labResultRepository;
        this.studentService = studentService;
    }

    public LabResult getOne(int id) {
        return labResultRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<LabResult> getAllLabResults() {
        return labResultRepository.findAll();
    }

    public Iterable<LabResult> findLabResultsForStudent(int telegramId) {
        return labResultRepository.findAllByStudent(studentService.getStudentByTelegramId(telegramId));
    }
}
