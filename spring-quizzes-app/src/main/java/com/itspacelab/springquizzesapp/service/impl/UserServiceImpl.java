package com.itspacelab.springquizzesapp.service.impl;

import com.itspacelab.springquizzesapp.dto.CredentialsDto;
import com.itspacelab.springquizzesapp.dto.SignUpDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.User;
import com.itspacelab.springquizzesapp.exception.AppException;
import com.itspacelab.springquizzesapp.mappers.UserMapper;
import com.itspacelab.springquizzesapp.repository.UserRepository;
import com.itspacelab.springquizzesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto register(SignUpDto dto) {
        Optional<User> userFromDB = userRepository.findByUsername(dto.username());

        if (userFromDB.isPresent())
            throw new AppException("login already exists", HttpStatus.BAD_REQUEST);

        User user = userMapper.signUpToUser(dto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(dto.password())));

        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto login(CredentialsDto credentialsDto) throws AppException {
        Optional<User> byEmail = userRepository.findByUsername(credentialsDto.username());
        if (byEmail.isEmpty()) {
            log.error("User email is incorrect");
            throw new AppException("Unknown user", HttpStatus.NOT_FOUND);
        }

        User verified = byEmail.get();
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), verified.getPassword())) {
            return userMapper.toUserDto(verified);
        }

        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }


}
