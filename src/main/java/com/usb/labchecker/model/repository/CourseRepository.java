package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Iterable<Course> getAllByGroup(Group group);
}
