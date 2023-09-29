package com.itspacelab.springquizzesapp.service;

import com.itspacelab.springquizzesapp.dto.CredentialsDto;
import com.itspacelab.springquizzesapp.dto.SignUpDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.User;
import com.itspacelab.springquizzesapp.exception.AppException;

public interface UserService {
    UserDto register(SignUpDto signUpDto);

    UserDto login(CredentialsDto credentialsDto) throws AppException;

    User findByUsername(String username);
}
