package com.itspacelab.springquizzesapp.mappers;

import com.itspacelab.springquizzesapp.dto.SignUpDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T22:35:37+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.type( user.getType() );
        userDto.surname( user.getSurname() );
        userDto.username( user.getUsername() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.type( dto.type() );
        user.name( dto.name() );
        user.surname( dto.surname() );
        user.username( dto.username() );

        return user.build();
    }
}
