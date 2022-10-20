package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.hr.spring.model.entity.ExamQuestion} entity
 */
@Data
public class ExamQuestionDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String question;
}