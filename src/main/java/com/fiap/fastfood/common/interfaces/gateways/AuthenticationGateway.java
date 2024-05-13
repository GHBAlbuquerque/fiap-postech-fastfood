package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;

public interface AuthenticationGateway {

    Boolean createUserAuthentication(String userName,
                                     String password,
                                     String email) throws IdentityProviderRegistrationException;


}
