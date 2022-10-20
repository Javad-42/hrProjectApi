package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.hr.spring.model.entity.UserTraining} entity
 */
@Data
public class UserTrainingDto implements Serializable {
    private final Integer id;
    private final LocalDate endDate;
    private final LocalDate startDate;
    @NotNull
    private final Short status;
}