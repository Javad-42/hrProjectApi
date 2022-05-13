package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.TrainingDTO;
import com.hr.spring.model.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainingMapper {
    TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

    TrainingDTO modelToDto(Training training);

    Training dtoToModel(TrainingDTO trainingDTO);
}
