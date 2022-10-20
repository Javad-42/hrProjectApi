package com.hr.spring.model.dto.request;

import lombok.Data;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @NotNull
    @Size(min = 3, max = 20)
    private String username;
    private String name;
    private String surname;
    private String father_name;
    private Date year_of_birth;
    private String phone;
    @NotNull
    @Size(max = 50)
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 40)
    private String password;
    private String position;
    private Integer companyId;

}
