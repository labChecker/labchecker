package com.usb.labchecker.model.dto;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class LabByStudentIdAndSubjectIdDto {

    private Integer id;
    private Integer subjectId;
    private Integer number;
    private String description;
    private List<Map<String, String>> docs;

}
