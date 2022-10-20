package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.UserTrainingDto;
import com.hr.spring.model.entity.UserTraining;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserTrainingMapper {
    UserTraining dtoToModel(UserTrainingDto userTrainingDto);

    UserTrainingDto modelToDto(UserTraining userTraining);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserTraining updateUserTrainingFromUserTrainingDto(UserTrainingDto userTrainingDto, @MappingTarget UserTraining userTraining);
}
