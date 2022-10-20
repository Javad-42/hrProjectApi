package com.hr.spring.repository;

import com.hr.spring.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Short> {
}