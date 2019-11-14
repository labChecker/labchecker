package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Lab;
import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabResultRepository extends CrudRepository<LabResult, Integer> {
    Iterable<LabResult> findAllByStudent(Student student);
    Iterable<LabResult> findAllByLabIn(List<Lab> labList);
}
