package com.usb.labchecker.model.dto;

import lombok.Builder;

@Builder
public class LabResultBySubjectIdAndStudentIdDto {

    private Integer labResultId;
    private Integer subjectId;
    private Integer labId;
    private Double result;

}
