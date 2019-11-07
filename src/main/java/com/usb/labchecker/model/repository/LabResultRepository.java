package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.LabResult;
import com.usb.labchecker.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultRepository extends CrudRepository<LabResult, Integer> {
    Iterable<LabResult> findAllByStudent(Student student);
}
