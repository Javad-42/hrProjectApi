package com.hr.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "company", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private Date registration_date;

    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private List<User> userList;

    @OneToMany(mappedBy = "companies")
    List<CompanyTraining> companyTrainings;
}
