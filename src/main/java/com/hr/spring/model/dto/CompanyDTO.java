package com.hr.spring.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String email;
    private Date registration_date;
}
