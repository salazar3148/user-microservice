package com.pragma.powerup.usermicroservice.adapters.driving.http.jwt;

import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

public class JwtMailExtractor implements MailExtractor {

    @Value("${jwt.secret}")
    String secret;

    @Override
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
