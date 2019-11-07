package com.usb.labchecker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Table(name = "labs")
@Entity
@Data
@Getter
public class Lab {

    @Id
    @JsonIgnore
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @Column(name = "number")
    private Integer labNumber;

    @Column(name = "theme")
    private String labTheme;
}
