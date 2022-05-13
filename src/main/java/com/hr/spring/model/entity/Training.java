package com.hr.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "training", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String video_url;
    private Integer status;

    @OneToMany(mappedBy = "trainings")
    List<CompanyTraining> companyTrainings;

    @OneToMany(mappedBy = "training")
    List<UserTraining> userTrainings;
}
