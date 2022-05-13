package com.hr.spring.model.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class UserTrainingDTO {
    private Long id;
    private Date start_date;
    private Date end_date;
    private int status;
    private Long userId;
    private Long trainingId;
}
