package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Course;
import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getOne(int id) {
        return courseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Course> getAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    public List<Course> getAllCoursesForGroupId(Group group) {
        return courseRepository.getAllByGroup(group);
    }
}
