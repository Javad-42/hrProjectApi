package com.hr.spring.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "training")
public class Training implements Serializable {
    private static final long serialVersionUID = -1718115493331279522L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Short status;

    @Size(max = 255)
    @Column(name = "video_url")
    private String videoUrl;

    @Builder.Default
    @OneToMany(mappedBy = "training")
    private Set<UserTraining> userTrainings = new LinkedHashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "training")
    private List<CompanyTraining> companyTrainings = new ArrayList<>();

}