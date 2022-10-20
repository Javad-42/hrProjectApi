package com.hr.spring.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.hr.spring.model.entity.CompanyTraining} entity
 */
@Data
public class CompanyTrainingDto implements Serializable {
    private final Integer id;
    private final LocalDate buyDate;
}