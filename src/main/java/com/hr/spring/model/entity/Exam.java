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
@Table(name = "exam")
public class Exam implements Serializable {
    private static final long serialVersionUID = 6907398987408637279L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> examQuestions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exam")
    private List<UserExam> userExams = new ArrayList<>();

}