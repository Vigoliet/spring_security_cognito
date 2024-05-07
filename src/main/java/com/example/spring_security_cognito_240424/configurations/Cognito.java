package com.example.spring_security_cognito_240424.configurations;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import java.util.ArrayList;
import java.util.List;

public class Cognito {
    private static CognitoIdentityProviderClient getClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    public static Boolean signUp(String clientId, String username, String password, String email) {
        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        List<AttributeType> userAttrsList = new ArrayList<>();
        userAttrsList.add(userAttrs);
        try {
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(userAttrsList)
                    .username(username)
                    .clientId(clientId)
                    .password(password)
                    .build();

            getClient().signUp(signUpRequest);
            System.out.println("User has been signed up ");

            return true;

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return false;
    }


    public static void confirmUser(String poolId, String username) {
        try {
            AdminConfirmSignUpRequest request = AdminConfirmSignUpRequest.builder()
                    .userPoolId(poolId)
                    .username(username)
                    .build();

            getClient().adminConfirmSignUp(request);

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }

    }
}

