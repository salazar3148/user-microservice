package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler {

    @Value("${aws.cognito.clientId}")
    private String clientId;

    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    @Override
    public JwtResponseDto login(LoginRequestDto loginRequestDto) {
        final Map<String, String> authParam = new HashMap<>();
        authParam.put("USERNAME", loginRequestDto.getMail());
        authParam.put("PASSWORD", loginRequestDto.getPassword());
        InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withAuthParameters(authParam);
        InitiateAuthResult initiateAuthResult = awsCognitoIdentityProvider.initiateAuth(initiateAuthRequest);
        return new JwtResponseDto(initiateAuthResult.getAuthenticationResult().getAccessToken());
    }
}
