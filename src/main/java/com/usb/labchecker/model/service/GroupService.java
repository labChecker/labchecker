package com.usb.labchecker.model.service;

import com.usb.labchecker.model.dto.GroupByIdDto;
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

    public List<Group> getAllGroups() {
        return (List<Group>)groupRepository.findAll();
    }

    public Group getByName(String name) {
        return groupRepository.findByName(name).orElseThrow(NoSuchElementException::new);
    }

    public List<GroupByIdDto> getGroupList() {
        List <Group> groups = getAllGroups();
        return groups.stream()
                .map(e -> GroupByIdDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                .build())
                .collect(Collectors.toList());
    }

}
