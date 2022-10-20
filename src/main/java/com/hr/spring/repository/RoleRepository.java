package com.hr.spring.repository;

import com.hr.spring.model.ERole;
import com.hr.spring.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByName(ERole role);
}