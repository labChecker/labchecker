package com.usb.labchecker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Table(name = "students")
@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "telegram_id")
    private Integer telegramId;


}
