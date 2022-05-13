package com.hr.spring.repository.examsRepository;

import com.hr.spring.model.entity.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long> {
}
