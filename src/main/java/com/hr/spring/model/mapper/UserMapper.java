package com.hr.spring.model.mapper;

import com.hr.spring.model.dto.UserDTO;
import com.hr.spring.model.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = StatusMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "company.id", target = "companyId")
    UserDTO modelToDto(User user);

    @InheritInverseConfiguration
    User dtoToModel(UserDTO userDTO);
}
