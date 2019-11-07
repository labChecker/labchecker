package com.usb.labchecker.model.dto;

import lombok.Data;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer telegramId;
    private String groupName;
    private String githubLink;
}