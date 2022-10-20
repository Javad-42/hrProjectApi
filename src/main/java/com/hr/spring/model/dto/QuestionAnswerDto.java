package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.hr.spring.model.entity.QuestionAnswer} entity
 */
@Data
public class QuestionAnswerDto implements Serializable {
    private final Long id;
    @Size(max = 255)
    private final String answer;
    private final Integer score;
}