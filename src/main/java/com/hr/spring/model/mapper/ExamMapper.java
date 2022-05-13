package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.model.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDTO modelToDto(Exam exam);

    Exam dtoToModel(ExamDTO examDTO);
}
