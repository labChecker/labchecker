package com.usb.labchecker.model.dto;

import lombok.Builder;
import java.util.List;

@Builder
public class LabByStudentIdDto {

    private Integer id;
    private Integer subjectId;
    private Integer number;
    private String description;
    private List<DocsDto> docs;

}
