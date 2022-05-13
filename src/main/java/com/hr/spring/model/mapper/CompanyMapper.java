package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.CompanyDTO;
import com.hr.spring.model.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO modelToDto(Company company);

    Company dtoToModel(CompanyDTO companyDTO);
}
