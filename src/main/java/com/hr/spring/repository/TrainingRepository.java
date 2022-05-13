package com.hr.spring.repository;

import com.hr.spring.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TrainingRepository extends JpaRepository<Training,Long> {

}
