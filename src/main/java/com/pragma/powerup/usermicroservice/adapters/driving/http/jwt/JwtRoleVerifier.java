package com.pragma.powerup.usermicroservice.adapters.driving.http.jwt;

import com.pragma.powerup.usermicroservice.domain.api.IRoleVerifier;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import static com.pragma.powerup.usermicroservice.configuration.Constants.ROLE_OWNER;

public class JwtRoleVerifier implements IRoleVerifier {

    @Value("${jwt.secret}")
    String secret;

    @Override
    public boolean isOwner(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("role")
                .equals(ROLE_OWNER);
    }
}
