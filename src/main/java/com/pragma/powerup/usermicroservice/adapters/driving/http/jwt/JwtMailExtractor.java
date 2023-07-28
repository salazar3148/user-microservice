package com.pragma.powerup.usermicroservice.adapters.driving.http.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtMailExtractor implements MailExtractor {

    private final JwtProvider jwtProvider;
    @Override
    public String extractEmail(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        DecodedJWT jwt = jwtProvider.getDecodedJwt(token);
        String userName = jwt.getClaim("username").toString();
        return userName.replace("\"", "");
    }
}
