package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    List<Course> getAllByGroup(Group group);
    List<Course> findAllBySubject(Subject subject);
}
