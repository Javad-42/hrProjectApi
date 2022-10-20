package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.QuestionAnswerDto;
import com.hr.spring.model.entity.QuestionAnswer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QuestionAnswerMapper {
    QuestionAnswer questionAnswerDtoToQuestionAnswer(QuestionAnswerDto questionAnswerDto);

    QuestionAnswerDto questionAnswerToQuestionAnswerDto(QuestionAnswer questionAnswer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QuestionAnswer updateQuestionAnswerFromQuestionAnswerDto(QuestionAnswerDto questionAnswerDto, @MappingTarget QuestionAnswer questionAnswer);
}
