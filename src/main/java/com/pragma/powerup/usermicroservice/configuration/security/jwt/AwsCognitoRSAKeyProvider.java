package com.pragma.powerup.usermicroservice.configuration.security.jwt;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.interfaces.RSAKeyProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class AwsCognitoRSAKeyProvider implements RSAKeyProvider {
    private final URL awsStoreUrl;
    private final JwkProvider provider;

    public AwsCognitoRSAKeyProvider(String region, String idPoolUrl) {
        String jwtUrl = "https://cognito-idp.us-east-1.amazonaws.com/us-east-1_KUn4x9Gao/.well-known/jwks.json";
        try{
            awsStoreUrl = new URL(String.format(jwtUrl, region, idPoolUrl));
        } catch (MalformedURLException malformedURLException){
            throw new RuntimeException("Invalid url");
        }
        provider = new JwkProviderBuilder(awsStoreUrl).build();
    }

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        try {
            return (RSAPublicKey) provider.get(keyId).getPublicKey();
        } catch (JwkException exception) {
            throw new RuntimeException("Failed to get JWT");
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }
}
