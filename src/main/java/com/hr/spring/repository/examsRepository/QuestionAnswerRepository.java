package com.hr.spring.repository.examsRepository;

import com.hr.spring.model.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
}
