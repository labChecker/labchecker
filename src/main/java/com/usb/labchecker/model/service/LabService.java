package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.LabByIdDto;
import com.usb.labchecker.model.dto.LabByStudentIdDto;
import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.repository.CourseRepository;
import com.usb.labchecker.model.repository.LabRepository;
import com.usb.labchecker.model.repository.LabResultRepository;
import com.usb.labchecker.model.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LabService {
    private final LabRepository labRepository;
    private final StudentService studentService;
    private final CourseService courseService;
    private final LabResultRepository labResultRepository;
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    public LabService(LabRepository labRepository,
                      StudentService studentService,
                      CourseService courseService,
                      LabResultRepository labResultRepository,
                      SubjectRepository subjectRepository,
                      CourseRepository courseRepository) {
        this.labRepository = labRepository;
        this.studentService = studentService;
        this.courseService = courseService;
        this.labResultRepository = labResultRepository;
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;


    }

    public Lab getOne(int id) {
        return labRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Lab> getAllLabs() {
        return (List<Lab>)labRepository.findAll();
    }

    public List<LabByIdDto> getAllLabsForTelegramId(int telegramId) {
        Group group = studentService.getStudentByTelegramId(telegramId).getGroup();
        List<Course> courseList = new ArrayList<>(courseService.getAllCoursesForGroupId(group));
        return labRepository.findAllByCourseIsIn(courseList).stream()
                .map(e -> LabByIdDto.builder()
                        .id(e.getId())
                        .description(e.getLabTheme())
//                        .docs(e.getDocs())
                        .number(e.getLabNumber())
                .build())
                .collect(Collectors.toList());
    }

    public List<LabByStudentIdDto> getLabListByStudentId(Integer studentId) {
        List<LabByStudentIdDto> resultList = new ArrayList<>();
        labResultRepository.findAllByStudent(studentService.getOne(studentId))
                .forEach(e -> resultList.add(LabByStudentIdDto.builder()
                        .description(e.getLab().getLabTheme())
                        .id(e.getId())
                        .number(e.getLab().getLabNumber())
                        .subjectId(e.getLab()
                                .getCourse()
                                .getSubject()
                                .getId())
//                            .docs(e.getDocs())
                        .build()));
        return resultList;
    }

    public List<LabByStudentIdDto> getLabListByStudentIdAndSubjectId(Integer studentId, Integer subjectId) {
        List<Lab> labListByStudents = new ArrayList<>();
        labResultRepository.findAllByStudent(studentService.getOne(studentId))
                .forEach(e -> labListByStudents.add(e.getLab()));

        List<Lab> labListBySubjects = labRepository.findAllByCourseIsIn(courseRepository.findAllBySubject(subjectRepository
                .findById(subjectId)
                .orElseThrow(NoSuchElementException::new)));

        return labListByStudents.stream()
                .distinct()
                .filter(labListBySubjects::contains)
                .map(e -> LabByStudentIdDto.builder()
                            .description(e.getLabTheme())
                            .id(e.getId())
                            .number(e.getLabNumber())
                            .subjectId(subjectId)
//                            .docs(e.getDocs())
                            .build())
                .collect(Collectors.toList());

    }



}
