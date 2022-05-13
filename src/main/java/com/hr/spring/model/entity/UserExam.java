package com.hr.spring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_exam", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short user_score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userE;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exams;
}
