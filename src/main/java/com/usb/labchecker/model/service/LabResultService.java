package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.LabResultDto;
import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.repository.CourseRepository;
import com.usb.labchecker.model.repository.LabRepository;
import com.usb.labchecker.model.repository.LabResultRepository;
import com.usb.labchecker.model.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LabResultService {
    private final LabResultRepository labResultRepository;
    private final StudentService studentService;
    private final LabRepository labRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public LabResultService(LabResultRepository labResultRepository,
                            StudentService studentService,
                            LabRepository labRepository,
                            CourseRepository courseRepository,
                            SubjectRepository subjectRepository) {
        this.labResultRepository = labResultRepository;
        this.studentService = studentService;
        this.labRepository = labRepository;
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    public LabResult getOne(int id) {
        return labResultRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<LabResult> getAllLabResults() {
        return labResultRepository.findAll();
    }

    public Iterable<LabResult> findLabResultsForStudent(Integer studentId) {
        return labResultRepository.findAllByStudent(studentService.getOne(studentId));
    }

    public Set<LabResult> getLabResultsByStudentIdAndSubjectId(Integer studentId,
                                                               Integer subjectId) {
        List<LabResult> labResultListByStudent = (List<LabResult>) labResultRepository
                .findAllByStudent(studentService.getOne(studentId));
        List<LabResult> labResultListBySubject = (List<LabResult>) labResultRepository
                .findAllByLabIn((List<Lab>) labRepository
                .findAllByCourseIsIn(courseRepository
                .findAllBySubject(subjectRepository
                .findById(subjectId)
                .orElseThrow(NoSuchElementException::new))));

        return labResultListByStudent.stream()
                .distinct()
                .filter(labResultListBySubject::contains)
                .collect(Collectors.toSet());

    }

    public LabResult addLabResult(LabResultDto labResultDto) {
        LabResult labResultToAdd = LabResult.builder()
                .lab(labResultDto.getLab())
                .githubRepositoryLink(labResultDto.getGithubRepositoryLink())
                .mark(labResultDto.getMark())
                .student(labResultDto.getStudent())
                .variant(labResultDto.getVariant())
                .build();

        labResultRepository.save(labResultToAdd);
        return labResultToAdd;
    }
}
