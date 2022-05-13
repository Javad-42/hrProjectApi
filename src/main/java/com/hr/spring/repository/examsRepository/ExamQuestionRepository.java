package com.hr.spring.repository.examsRepository;

import com.hr.spring.model.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {
}
