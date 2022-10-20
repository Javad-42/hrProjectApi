package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.hr.spring.model.entity.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Long id;
    @Size(max = 50)
    private final String email;
    private final Integer experience;
    @Size(max = 50)
    private final String fatherName;
    @Size(max = 20)
    private final String maritalStatus;
    @Size(max = 30)
    private final String name;
    @Size(max = 15)
    private final String phone;
    @Size(max = 40)
    private final String position;
    @Size(max = 50)
    private final String surname;
    @Size(max = 50)
    private final String username;
    private final LocalDate yearOfBirth;
}