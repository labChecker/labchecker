package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Teacher;
import com.usb.labchecker.model.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getOne(int id) {
        return teacherRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
