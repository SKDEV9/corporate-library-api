package com.SKDEV9.corporate_library_api.infra.security;

import com.SKDEV9.corporate_library_api.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(User user) {
        try {

            Algorithm algoritmo = Algorithm.HMAC256(secret);

            // 2. Começamos a "imprimir" o crachá
            return JWT.create()
                    .withIssuer("Corporate Library API")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }


    public String getSubject(String tokenJWT) {
        try {

            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Corporate Library API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {

            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}