package com.hr.spring.repository;

import com.hr.spring.model.entity.Training;
import com.hr.spring.model.entity.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTrainingRepository extends JpaRepository<UserTraining, Long> {
}
