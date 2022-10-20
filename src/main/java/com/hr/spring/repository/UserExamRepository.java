package com.hr.spring.repository;

import com.hr.spring.model.entity.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExamRepository extends JpaRepository<UserExam, Integer> {
}