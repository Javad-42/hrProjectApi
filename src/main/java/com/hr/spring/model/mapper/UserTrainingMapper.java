package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.UserTrainingDTO;
import com.hr.spring.model.entity.UserTraining;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserTrainingMapper {
    UserTrainingMapper INSTANCE = Mappers.getMapper(UserTrainingMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "training.id", target = "trainingId")
    UserTrainingDTO modelToDto(UserTraining userTraining);

    @InheritInverseConfiguration
    UserTraining dtoToModel(UserTrainingDTO userTrainingDTO);

}
