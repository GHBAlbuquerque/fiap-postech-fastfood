package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
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

    @Value("aws.cognito.clientid")
    private String identityProviderClientId;

    @Value("aws.cognito.clientSecretKey")
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
            String secretVal = calculateSecretHash(identityProviderClientId, identityProviderClientSecretKey, userName);

            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(attrs)
                    .username(userName)
                    .clientId(identityProviderClientId)
                    .password(password)
                    .secretHash(secretVal)
                    .build();

            var signUpResponse = identityProviderClient.signUp(signUpRequest);

            return signUpResponse.userConfirmed();

        } catch (CognitoIdentityProviderException e) {
            throw new IdentityProviderRegistrationException(String.valueOf(e.statusCode()), e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IdentityProviderRegistrationException(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
        }
    }

    private String calculateSecretHash(String userPoolClientId,
                                       String userPoolClientSecret,
                                       String userName)

            throws NoSuchAlgorithmException, InvalidKeyException {

        var signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);

        var mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        mac.update(userName.getBytes(StandardCharsets.UTF_8));
        byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
        return java.util.Base64.getEncoder().encodeToString(rawHmac);
    }
}
