package com.hr.spring.model.dto;

import lombok.Data;

@Data
public class UserExamDTO {
    private Long id;
    private Short user_score;
    private Long userId;
    private Long examId;
}
