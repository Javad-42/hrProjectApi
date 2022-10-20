package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.TrainingDto;
import com.hr.spring.model.entity.Training;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TrainingMapper {
    Training dtoToModel(TrainingDto trainingDto);

    TrainingDto modelToDto(Training training);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Training updateTrainingFromTrainingDto(TrainingDto trainingDto, @MappingTarget Training training);
}
