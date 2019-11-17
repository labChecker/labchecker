package com.usb.labchecker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "lab_results")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LabResult {

    @Id
    @JsonIgnore
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_lab")
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_variant")
    private Variant variant;

    @Column(name = "github_repository_link")
    private String githubRepositoryLink;

    @Column(name = "mark")
    private Double mark;

}
