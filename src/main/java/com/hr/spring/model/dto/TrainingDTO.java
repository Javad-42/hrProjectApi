package com.hr.spring.model.dto;


import lombok.Data;

@Data
public class TrainingDTO {
    private Long id;
    private String name;
    private String description;
    private String video_url;
    private Integer status;
}
