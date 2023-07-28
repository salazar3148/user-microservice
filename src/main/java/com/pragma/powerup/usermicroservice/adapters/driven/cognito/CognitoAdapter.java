package com.pragma.powerup.usermicroservice.adapters.driven.cognito;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.ICognitoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class CognitoAdapter implements ICognitoServicePort {

    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;
    private final UserCognitoDtoMapper userCognitoDtoMapper;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Override
    public void signUpUser(User user) {
        UserCognitoDto userCognitoDto = userCognitoDtoMapper.userToUserCognitoDto(user);
        AttributeType attributeType = new AttributeType().withName("email").withValue(userCognitoDto.getMail());
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.withClientId(clientId)
                .withPassword(userCognitoDto.getPassword())
                .withUsername(userCognitoDto.getMail())
                .withUserAttributes(attributeType);

        awsCognitoIdentityProvider.signUp(signUpRequest);

        AdminConfirmSignUpRequest confirmSignUpRequest = new AdminConfirmSignUpRequest()
                .withUserPoolId(userPoolId)
                .withUsername(userCognitoDto.getMail());

        awsCognitoIdentityProvider.adminConfirmSignUp(confirmSignUpRequest);
    }
}
