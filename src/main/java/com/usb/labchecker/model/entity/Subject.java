package com.usb.labchecker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "subjects")
@Entity
@Data
@Getter
public class Subject {

    @Id
    @JsonIgnore
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
