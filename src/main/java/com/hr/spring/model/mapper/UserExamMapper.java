package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.UserExamDto;
import com.hr.spring.model.entity.UserExam;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserExamMapper {
    UserExam dtoToModel(UserExamDto userExamDto);

    UserExamDto modelToDto(UserExam userExam);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserExam updateUserExamFromUserExamDto(UserExamDto userExamDto, @MappingTarget UserExam userExam);
}
