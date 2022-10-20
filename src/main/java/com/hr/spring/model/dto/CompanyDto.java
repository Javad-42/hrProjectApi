package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link com.hr.spring.model.entity.Company} entity
 */
@Data
public class CompanyDto implements Serializable {
    private final Integer id;
    @Size(max = 50)
    private final String email;
    @Size(max = 50)
    private final String name;
    private final LocalDate registrationDate;
    private final List<UserDto> users;

    /**
     * A DTO for the {@link com.hr.spring.model.entity.User} entity
     */
    @Data
    public static class UserDto implements Serializable {
        private final Long id;
        @Size(max = 50)
        private final String username;
    }
}