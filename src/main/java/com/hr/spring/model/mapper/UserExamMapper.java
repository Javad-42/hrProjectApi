package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.UserExamDTO;
import com.hr.spring.model.entity.UserExam;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserExamMapper {
    UserExamMapper INSTANCE = Mappers.getMapper(UserExamMapper.class);

    @Mapping(source = "userE.id", target = "userId")
    @Mapping(source = "exams.id", target = "examId")
    UserExamDTO modelToDto(UserExam userExam);

    @InheritInverseConfiguration
    UserExam dtoToModel(UserExamDTO userExamDTO);
}
