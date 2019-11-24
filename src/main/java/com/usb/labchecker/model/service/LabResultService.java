package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.LabResultByStudentIdDto;
import com.usb.labchecker.model.dto.LabResultDto;
import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LabResultService {
    private final LabResultRepository labResultRepository;
    private final StudentRepository studentRepository;
    private final LabRepository labRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public LabResultService(LabResultRepository labResultRepository,
                            StudentRepository studentRepository,
                            LabRepository labRepository,
                            CourseRepository courseRepository,
                            SubjectRepository subjectRepository) {
        this.labResultRepository = labResultRepository;
        this.studentRepository = studentRepository;
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

    public Set<LabResultByStudentIdDto> findLabResultsForStudent(Integer studentId) {

        return labResultRepository
                .findAllByStudent(studentRepository.getOne(studentId))
                .stream()
                .map(e-> LabResultByStudentIdDto.builder()
                            .labResultId(e.getId())
                            .labId(e.getLab().getId())
                            .result(e.getMark())
                            .subjectId(e.getLab()
                                    .getCourse()
                                    .getSubject()
                                    .getId())
                            .build())
                .collect(Collectors.toSet());
    }

    public Set<LabResultByStudentIdDto> getLabResultsByStudentIdAndSubjectId(Integer studentId,
                                                                             Integer subjectId) {
        List<LabResult> labResultListByStudent = labResultRepository
                .findAllByStudent(studentRepository.getOne(studentId));
        List<LabResult> labResultListBySubject = labResultRepository
                .findAllByLabIn(labRepository
                .findAllByCourseIsIn(courseRepository
                .findAllBySubject(subjectRepository
                .findById(subjectId)
                .orElseThrow(NoSuchElementException::new))));

        return labResultListByStudent.stream()
                .distinct()
                .filter(labResultListBySubject::contains)
                .map(e-> LabResultByStudentIdDto.builder()
                        .labResultId(e.getId())
                        .labId(e.getLab().getId())
                        .result(e.getMark())
                        .subjectId(subjectRepository
                                .findById(subjectId)
                                .orElseThrow(NoSuchElementException::new)
                                .getId())
                        .build())
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
