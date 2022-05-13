package com.hr.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exam", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<ExamQuestion> examQuestionList;

    @OneToMany(mappedBy = "exams",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<UserExam> exams;
}
