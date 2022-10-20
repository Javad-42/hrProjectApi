package com.hr.spring.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 5808438548904183851L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "experience")
    private Integer experience;

    @Size(max = 50)
    @Column(name = "father_name", length = 50)
    private String fatherName;

    @Size(max = 20)
    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Size(max = 30)
    @Column(name = "name", length = 30)
    private String name;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @Size(max = 40)
    @Column(name = "positions", length = 40)
    private String position;

    @Size(max = 50)
    @Column(name = "surname", length = 50)
    private String surname;

    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "year_of_birth")
    private LocalDate yearOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserTraining> userTrainings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserExam> userExams = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Status> statuses = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}