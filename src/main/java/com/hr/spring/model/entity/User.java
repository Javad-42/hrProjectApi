package com.hr.spring.model.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    private String name;
    private String surname;
    private String father_name;
    private Date year_of_birth;
    private String phone;
    @NotNull
    private String position;
    private Integer experience;
    private String maritalStatus;

    @OneToMany(mappedBy = "userStatus")
    private List<Status> statuses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user")
    private List<UserTraining> trainings;

    @OneToMany(mappedBy = "userE")
    private List<UserExam> exams;

    public User(String username, String email, String password, String name, String surname,
                String father_name,
                Date year_of_birth,
                String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.father_name = father_name;
        this.year_of_birth = year_of_birth;
        this.phone = phone;
    }

    public User(String username, String email, String password, String name, String surname,
                String father_name,
                Date year_of_birth,
                String phone,
                String position,
                Company company) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.father_name = father_name;
        this.year_of_birth = year_of_birth;
        this.phone = phone;
        this.position = position;
        this.company = company;
    }
}
