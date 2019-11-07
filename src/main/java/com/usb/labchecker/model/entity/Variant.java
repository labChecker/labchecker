package com.usb.labchecker.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "variants")
@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Variant {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_lab")
    private Lab lab;

    @Column(name = "number")
    private int number;

    @Column(name = "testfile_path")
    private String testfilePath;
}
