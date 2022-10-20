package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.CompanyTrainingDto;
import com.hr.spring.model.entity.CompanyTraining;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompanyTrainingMapper {
    CompanyTraining dtoToModel(CompanyTrainingDto companyTrainingDto);

    CompanyTrainingDto modelToDto(CompanyTraining companyTraining);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CompanyTraining updateCompanyTrainingFromCompanyTrainingDto(CompanyTrainingDto companyTrainingDto, @MappingTarget CompanyTraining companyTraining);
}
