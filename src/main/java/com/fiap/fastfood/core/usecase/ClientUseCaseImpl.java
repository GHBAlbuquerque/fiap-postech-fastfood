package com.fiap.fastfood.core.usecase;

import com.fiap.fastfood.common.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;
import com.fiap.fastfood.common.interfaces.gateways.AuthenticationGateway;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.common.interfaces.usecase.ClientUseCase;
import com.fiap.fastfood.core.entity.Client;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

public class ClientUseCaseImpl implements ClientUseCase {

    @Override
    public Client registerClient(Client client,
                                 ClientGateway clientGateway,
                                 AuthenticationGateway authenticationGateway)
            throws AlreadyRegisteredException, IdentityProviderRegistrationException {
        final var cpfInUse = validateCpfInUse(client.getCpf(), clientGateway);
        final var validationResult = Client.validate(client, cpfInUse);

        if (!validationResult.getIsValid()) {
            throw new AlreadyRegisteredException(
                    "CLIENT-01",
                    "Couldn't complete registration for client.",
                    validationResult.getErrors()
            );
        }

        authenticationGateway.createUserAuthentication(client.getCpf(),
                "FIAPauth123_",
                client.getEmail());

        return clientGateway.saveClient(client);
    }

    @Override
    public Client getClientByCpf(String cpf, ClientGateway clientGateway)
            throws EntityNotFoundException {
        final var client = clientGateway.getClientByCpf(cpf);

        if (client == null) {
            throw new EntityNotFoundException(
                    "CLIENT-02",
                    "Client not found."
            );
        }

        return client;
    }

    @Override
    public Boolean validateCpfInUse(String cpf, ClientGateway clientGateway) {
        final var clientUsingCpf = clientGateway.getClientByCpf(cpf);

        return clientUsingCpf != null;
    }

    @Override
    public Boolean confirmClientSignUp(String cpf, String code, AuthenticationGateway authenticationGateway)
            throws IdentityProviderRegistrationException {
        return authenticationGateway.confirmSignUp(cpf, code);
    }
}
