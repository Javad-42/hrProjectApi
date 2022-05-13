package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.ExamQuestionDTO;
import com.hr.spring.model.entity.ExamQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExamQuestionMapper {
    ExamQuestionMapper INSTANCE = Mappers.getMapper(ExamQuestionMapper.class);

    ExamQuestionDTO modelToDto(ExamQuestion examQuestion);

    ExamQuestion dtoToModel(ExamQuestionDTO examQuestionDTO);
}
