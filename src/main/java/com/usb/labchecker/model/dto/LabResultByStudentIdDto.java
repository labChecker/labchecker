package com.usb.labchecker.model.dto;

import lombok.Builder;

@Builder
public class LabResultByStudentIdDto {

    private Integer id;
    private Integer subjectId;
    private Integer labId;
    private Double result;

}
