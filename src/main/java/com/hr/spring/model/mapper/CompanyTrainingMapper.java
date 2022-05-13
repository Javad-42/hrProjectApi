package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.CompanyTrainingDTO;
import com.hr.spring.model.entity.CompanyTraining;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyTrainingMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "trainings.id", target = "trainingId")
    @Mapping(source = "companies.id", target = "companyId")
    CompanyTrainingDTO modelToDto(CompanyTraining companyTraining);

    @InheritInverseConfiguration
    CompanyTraining dtoToModel(CompanyTrainingDTO companyTrainingDTO);

}
