package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.CompanyDto;
import com.hr.spring.model.entity.Company;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompanyMapper {
    Company dtoToModel(CompanyDto companyDto);

    CompanyDto modelToDto(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Company updateCompanyFromCompanyDto(CompanyDto companyDto, @MappingTarget Company company);

    @AfterMapping
    default void linkUsers(@MappingTarget Company company) {
        company.getUsers().forEach(user -> user.setCompany(company));
    }
}
