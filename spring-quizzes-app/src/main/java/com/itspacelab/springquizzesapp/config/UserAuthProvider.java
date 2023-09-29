package com.itspacelab.springquizzesapp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itspacelab.springquizzesapp.dto.UserDto;
import com.itspacelab.springquizzesapp.entity.User;
import com.itspacelab.springquizzesapp.entity.types.UserType;
import com.itspacelab.springquizzesapp.exception.AppException;
import com.itspacelab.springquizzesapp.mappers.UserMapper;
import com.itspacelab.springquizzesapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto userDto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);

        return JWT.create()
                .withIssuer(userDto.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(validity)
                .withClaim("name", userDto.getName())
                .withClaim("type", userDto.getType().ordinal())
                .withClaim("surname", userDto.getSurname())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .username(decoded.getIssuer())
                .name(decoded.getClaim("name").asString())
                .surname(decoded.getClaim("surname").asString())
                .type(decoded.getClaim("type").as(UserType.class))
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(user.getType().name()));
    }

    public Authentication validateTokenStrong(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        Optional<User> byUsername = userRepository.findByUsername(decoded.getIssuer());
        if (byUsername.isEmpty()) throw new AppException("Unknown user", HttpStatus.NOT_FOUND);

        return new UsernamePasswordAuthenticationToken(
                userMapper.toUserDto(byUsername.get()),
                null,
                AuthorityUtils.createAuthorityList(byUsername.get().getType().name()));
    }
}
