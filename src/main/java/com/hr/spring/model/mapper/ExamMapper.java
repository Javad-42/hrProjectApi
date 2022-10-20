package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.ExamDto;
import com.hr.spring.model.entity.Exam;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExamMapper {
    Exam dtoToModel(ExamDto examDto);

    ExamDto modelToDto(Exam exam);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Exam updateExamFromExamDto(ExamDto examDto, @MappingTarget Exam exam);
}
