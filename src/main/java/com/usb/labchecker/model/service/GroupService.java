package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getOne(int id) {
        return groupRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getByName(String name) {
        return groupRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

}
