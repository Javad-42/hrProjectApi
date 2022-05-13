package com.hr.spring.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;


@Data
public class UserDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    private String email;
    private String name;
    private String surname;
    private String father_name;
    private Date year_of_birth;
    private String phone;
    private String position;
    private Integer experience;
    private String maritalStatus;
    private List<StatusDto> statuses;
    private Long companyId;
}
