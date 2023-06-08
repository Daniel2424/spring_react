package com.ruzhkov.jwt.backend.mappers;

import com.ruzhkov.jwt.backend.dtos.SignUpDto;
import com.ruzhkov.jwt.backend.dtos.UserDto;
import com.ruzhkov.jwt.backend.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
