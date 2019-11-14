package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.entity.Subject;
import com.usb.labchecker.model.repository.CourseRepository;
import com.usb.labchecker.model.repository.LabRepository;
import com.usb.labchecker.model.repository.LabResultRepository;
import com.usb.labchecker.model.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
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

    public Iterable<Lab> getAllLabs() {
        return labRepository.findAll();
    }

    public Iterable<Lab> getAllLabsForTelegramId(int telegramId) {
        List<Course> courseList = new ArrayList<>();
        Group group = studentService.getStudentByTelegramId(telegramId).getGroup();
        courseService.getAllCoursesForGroupId(group).forEach(courseList::add);
        return labRepository.findAllByCourseIsIn(courseList);
    }

    public List<Lab> getLabListByStudentId(Integer studentId) {
        List<Lab> resultList = new ArrayList<>();
        labResultRepository.findAllByStudent(studentService.getOne(studentId))
                .forEach(e -> resultList.add(e.getLab()));
        return resultList;
    }

    public Set<Lab> getLabListByStudentIdAndSubjectId(Integer studentId, Integer subjectId) {
        List<Lab> labListByStudents = new ArrayList<>();
        labResultRepository.findAllByStudent(studentService.getOne(studentId))
                .forEach(e -> labListByStudents.add(e.getLab()));

        List<Lab> labListBySubjects = (List<Lab>) labRepository.findAllByCourseIsIn(courseRepository.findAllBySubject(subjectRepository
                .findById(subjectId)
                .orElseThrow(NoSuchElementException::new)));

        return labListByStudents.stream()
                .distinct()
                .filter(labListBySubjects::contains)
                .collect(Collectors.toSet());

    }



}
