package com.usb.labchecker.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class LabByIdDto {

    private Integer id;
    private Integer number;
    private String description;
    private List<DocsDto> docs;
}
