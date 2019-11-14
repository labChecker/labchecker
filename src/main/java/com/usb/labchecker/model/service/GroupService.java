package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Group;
import com.usb.labchecker.model.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public Map<Integer, String> getGroupListMap() {
        List <Group> groups = (List<Group>) getAllGroups();
        return groups.stream()
                .collect(Collectors.toMap(Group::getId, Group::getName));
    }

}
