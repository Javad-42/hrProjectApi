package com.hr.spring.repository;

import com.hr.spring.model.entity.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTrainingRepository extends JpaRepository<UserTraining, Integer> {
}