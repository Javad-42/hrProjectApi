package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.StatusDto;
import com.hr.spring.model.entity.Status;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    @Mapping(source = "status.description", target = "status")
    StatusDto modelToDto(Status status);

    List<StatusDto> modelToDto(List<Status> statuses);

    @InheritInverseConfiguration
    List<Status> dtoToModel(List<StatusDto> statusDtos);

    @InheritInverseConfiguration
    Status dtoToModel(StatusDto statusDto);
}
