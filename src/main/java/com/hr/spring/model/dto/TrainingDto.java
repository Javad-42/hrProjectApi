package com.hr.spring.model.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.hr.spring.model.entity.Training} entity
 */
@Data
public class TrainingDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    private final String description;
    @Size(max = 255)
    private final String name;
    private final Short status;
    @Size(max = 255)
    private final String videoUrl;
}