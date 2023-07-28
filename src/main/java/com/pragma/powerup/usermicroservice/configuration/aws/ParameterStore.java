package com.pragma.powerup.usermicroservice.configuration.aws;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
public class ParameterStore {
    public static String getParameterValue(String parameterName) {
        SsmClient ssmClient = SsmClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        GetParameterRequest parameterRequest = GetParameterRequest.builder()
                .name(parameterName)
                .withDecryption(true)
                .build();
        return ssmClient.getParameter(parameterRequest).parameter().value();
    }
}
