package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.StudentDto;
import com.usb.labchecker.model.entity.Student;
import com.usb.labchecker.model.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupService groupService;

    public StudentService(StudentRepository studentRepository, GroupService groupService) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
    }

    public Student getOne(int id) {
        return studentRepository.getOne(id);
    }

    public Student createStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .githubLink(studentDto.getGithubLink())
                .group(groupService.getByName(studentDto.getGroupName()))
                .telegramId(studentDto.getTelegramId())
                .build();

        studentRepository.save(student);
        return student;
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByTelegramId(int telegramId) {
        return studentRepository.getByTelegramId(telegramId).orElseThrow(NoSuchElementException::new);
    }

    public Integer getStudentIdByTelegramId(Integer telegramId) {
        return studentRepository.getByTelegramId(telegramId).orElseThrow(NoSuchElementException::new).getId();
    }
//
//    public Integer getStudentVariantByGithubId(String githubId) {
//        return studentRepository.findByGithubLink(githubId).getVariant();
//    }
}
