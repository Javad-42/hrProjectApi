package com.hr.spring.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.hr.spring.model.entity.UserExam} entity
 */
@Data
public class UserExamDto implements Serializable {
    private final Integer id;
    private final Short userScore;
}