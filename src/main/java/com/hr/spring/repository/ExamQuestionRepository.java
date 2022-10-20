package com.hr.spring.repository;

import com.hr.spring.model.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Integer> {
}