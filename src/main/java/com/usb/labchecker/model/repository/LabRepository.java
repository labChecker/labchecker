package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Lab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends CrudRepository<Lab, Integer> {
    List<Lab> findAllByCourseIsIn(List<Course> courseList);
}
