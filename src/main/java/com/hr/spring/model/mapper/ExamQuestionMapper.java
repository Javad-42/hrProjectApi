package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.ExamQuestionDto;
import com.hr.spring.model.entity.ExamQuestion;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExamQuestionMapper {
    ExamQuestion dtoToModel(ExamQuestionDto examQuestionDto);

    ExamQuestionDto modelToDto(ExamQuestion examQuestion);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExamQuestion updateExamQuestionFromExamQuestionDto(ExamQuestionDto examQuestionDto, @MappingTarget ExamQuestion examQuestion);
}
