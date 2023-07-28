package com.pragma.powerup.usermicroservice.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    @Value("${aws.cognito.identityPoolUrl}")
    private String identityPoolUrl;

    @Value("${aws.cognito.region}")
    private String region;

    @Value("${aws.cognito.issuer}")
    private String issuer;
    private static final String USERNAME = "username";

    public DecodedJWT getDecodedJwt(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        RSAKeyProvider keyProvider = new AwsCognitoRSAKeyProvider(region, identityPoolUrl);
        Algorithm algorithm = Algorithm.RSA256(keyProvider);
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        return jwtVerifier.verify(token);
    }

}
