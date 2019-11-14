package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.GithubUserDto;
import com.usb.labchecker.model.dto.StudentDto;
import com.usb.labchecker.model.entity.Student;
import com.usb.labchecker.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class StudentService {

    private final String GITHUB_API_URL_PREFIX = "https://api.github.com/user/";
    private RestTemplate restTemplate;
    private final StudentRepository studentRepository;
    private final GroupService groupService;

    public StudentService(RestTemplateBuilder restTemplate,
                          StudentRepository studentRepository,
                          GroupService groupService) {
        this.restTemplate = restTemplate.build();
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

    public Integer getStudentVariantByGithubId(Integer githubId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<GithubUserDto> answer = restTemplate
                    .getForEntity(GITHUB_API_URL_PREFIX +
                            githubId, GithubUserDto.class);
            return studentRepository.findByGithubLink(Objects.requireNonNull(answer.getBody()).getLogin()).getVariant();
        } catch (Exception e) {

        }
        return 0;
    }
}
