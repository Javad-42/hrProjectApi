package com.hr.spring.repository;

import com.hr.spring.model.entity.CompanyTraining;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyTrainingRepository extends JpaRepository<CompanyTraining, Integer> {
}