package com.hr.spring.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExamDTO {
    private Long id;
    private String name;
    private List<ExamQuestionDTO> examQuestionList;
}
