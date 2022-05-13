package com.hr.spring.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CompanyTrainingDTO {
    private Long id;
    private Date buy_date;
    private Long trainingId;
    private Long companyId;

}
