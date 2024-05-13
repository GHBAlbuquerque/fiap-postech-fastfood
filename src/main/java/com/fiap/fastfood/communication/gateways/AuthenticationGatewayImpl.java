package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import com.fiap.fastfood.common.interfaces.gateways.AuthenticationGateway;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Component
public class AuthenticationGatewayImpl implements AuthenticationGateway {

    @Value("${aws.cognito.clientid}")
    private String identityProviderClientId;

    @Value("${aws.cognito.clientSecretKey}")
    private String identityProviderClientSecretKey;

    private final CognitoIdentityProviderClient identityProviderClient;

    private final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    private final String EMAIL_FIELD = "email";

    public AuthenticationGatewayImpl(CognitoIdentityProviderClient identityProviderClient) {
        this.identityProviderClient = identityProviderClient;
    }


    @Override
    public Boolean createUserAuthentication(String userName,
                                            String password,
                                            String email) throws IdentityProviderRegistrationException {

        var attributeType = AttributeType.builder()
                .name(EMAIL_FIELD)
                .value(email)
                .build();

        var attrs = new ArrayList<AttributeType>();
        attrs.add(attributeType);

        try {

            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(attrs)
                    .username(userName)
                    .clientId(identityProviderClientId)
                    .password(password)
                    .build();

            identityProviderClient.signUp(signUpRequest);
            System.out.println(userName + " was registered.");

            return true;

        } catch (CognitoIdentityProviderException e) {
            throw new IdentityProviderRegistrationException(String.valueOf(e.statusCode()), e.getMessage());
        }
    }

    public Boolean confirmSignUp(String userName, String code) throws IdentityProviderRegistrationException {
        try {
            ConfirmSignUpRequest signUpRequest = ConfirmSignUpRequest.builder()
                    .clientId(identityProviderClientId)
                    .confirmationCode(code)
                    .username(userName)
                    .build();

            identityProviderClient.confirmSignUp(signUpRequest);
            System.out.println(userName + " was confirmed.");

            return true;

        } catch (CognitoIdentityProviderException e) {
            throw new IdentityProviderRegistrationException(String.valueOf(e.statusCode()), e.getMessage());
        }
    }
}
