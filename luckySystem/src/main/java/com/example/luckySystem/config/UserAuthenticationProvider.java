package com.example.luckySystem.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.luckySystem.dto.user.UserDto;
import com.example.luckySystem.entity.Employee;
import com.example.luckySystem.service.user.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.auth0.jwt.JWTVerifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor

@RestController

public class UserAuthenticationProvider {

    @Value("${com.app.jwtSecret}")
    private String secretKey;

    private final UserService userService;

    @PostConstruct
    protected void init() {

        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withClaim("email", user.getEmail())
                .withClaim("employee", user.getEmployee())
                .withClaim("role", user.getRoles())
                .withClaim("contact", user.getContact())
                .withClaim("id", user.getId())
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = UserDto.builder()
                .username(decoded.getSubject())
                .username(decoded.getClaim("username").asString())
                .password(decoded.getClaim("password").asString())
                .email(decoded.getClaim("email").asString())
                .employee(decoded.getClaim("employee").asString())
                .roles(decoded.getClaim("role").asString())
                .contact(decoded.getClaim("contact").asString())
                .id(decoded.getClaim("id").asLong())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = userService.findByUsername(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

}
