package com.usb.labchecker.controller;

import com.usb.labchecker.model.dto.GroupByIdDto;
import com.usb.labchecker.model.service.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupByIdDto> getGroupListMap(){
        return groupService.getGroupList();
    }

}
