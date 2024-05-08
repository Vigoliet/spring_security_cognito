package com.example.spring_security_cognito_240424.configurations;

import io.github.cdimascio.dotenv.Dotenv;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.List;

public class Cognito {
    private static CognitoIdentityProviderClient getClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    public static Boolean signUp(String userPoolId, String username, String password, String email) {
        userPoolId = System.getProperty("aws.cognito.poolId"); // get the value of the aws.cognito.poolId property

        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        List<AttributeType> userAttrsList = new ArrayList<>();
        userAttrsList.add(userAttrs);
        try {
            AdminCreateUserRequest adminCreateUserRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId) // use the actual user pool ID here
                    .username(username)
                    .temporaryPassword(password)
                    .userAttributes(userAttrsList)
                    .build();

            getClient().adminCreateUser(adminCreateUserRequest);
            System.out.println("User has been created ");

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

