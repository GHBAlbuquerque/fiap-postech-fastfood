package com.fiap.fastfood.common.interfaces.usecase;

import com.fiap.fastfood.common.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;
import com.fiap.fastfood.common.interfaces.gateways.AuthenticationGateway;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.core.entity.Client;

public interface ClientUseCase {
    Client getClientByCpf(String cpf, ClientGateway clientGateway) throws EntityNotFoundException;

    Client registerClient(Client client, ClientGateway clientGateway, AuthenticationGateway authenticationGateway)
            throws AlreadyRegisteredException, IdentityProviderRegistrationException;

    Boolean validateCpfInUse(String cpf, ClientGateway clientGateway);

    Boolean confirmClientSignUp(String cpf, String code, AuthenticationGateway authenticationGateway) throws IdentityProviderRegistrationException;
}
