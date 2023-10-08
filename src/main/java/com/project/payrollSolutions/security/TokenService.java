package com.project.payrollSolutions.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.payrollSolutions.model.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    public String secret;

    public String generateToken(UserLogin user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.create().withIssuer("payroll").withSubject(user.getDocument()).withExpiresAt(this.generateExpirationDate()).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm).withIssuer("payroll").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
