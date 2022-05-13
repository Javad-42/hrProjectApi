package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.QuestionAnswerDTO;
import com.hr.spring.model.entity.QuestionAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {
    QuestionAnswerMapper INSTANCE = Mappers.getMapper(QuestionAnswerMapper.class);

    QuestionAnswerDTO modelToDto(QuestionAnswer questionAnswer);

    QuestionAnswer dtoToModel(QuestionAnswerDTO questionAnswerDTO);
}
