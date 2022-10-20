package com.hr.spring.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company implements Serializable {
    private static final long serialVersionUID = 6432388094379788555L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Builder.Default
    @OneToMany(mappedBy = "company")
    private List<CompanyTraining> companyTrainings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();

}