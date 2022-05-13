package com.hr.spring.repository;

import com.hr.spring.model.entity.CompanyTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTrainingRepository extends JpaRepository<CompanyTraining, Long> {
}
