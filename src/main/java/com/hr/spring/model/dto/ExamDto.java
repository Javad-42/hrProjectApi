package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.hr.spring.model.entity.Exam} entity
 */
@Data
public class ExamDto implements Serializable {
    private final Integer id;
    @Size(max = 50)
    private final String name;
}