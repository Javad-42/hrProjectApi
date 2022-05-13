package com.hr.spring.model.dto;

import lombok.Data;

@Data
public class QuestionAnswerDTO {
    private Long id;
    private String answer;
    private Integer score;
}
