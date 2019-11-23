package com.usb.labchecker.model.dto;

import lombok.Builder;

@Builder
public class SubjectByStudentIdDto {

    private Integer id;
    private String name;
    private String teacher;
}
