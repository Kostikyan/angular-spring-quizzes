package com.itspacelab.springquizzesapp.mappers;

import com.itspacelab.springquizzesapp.dto.SignUpDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);


    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto dto);
}
