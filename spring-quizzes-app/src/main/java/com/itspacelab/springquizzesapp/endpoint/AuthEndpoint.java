package com.itspacelab.springquizzesapp.endpoint;

import com.itspacelab.springquizzesapp.config.UserAuthProvider;
import com.itspacelab.springquizzesapp.dto.CredentialsDto;
import com.itspacelab.springquizzesapp.dto.SignUpDto;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.exception.AppException;
import com.itspacelab.springquizzesapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthEndpoint {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) throws AppException {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

}
