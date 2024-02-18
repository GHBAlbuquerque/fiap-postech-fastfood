package com.fiap.fastfood.application.services;

import com.fiap.fastfood.application.domain.Client;
import com.fiap.fastfood.application.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.application.port.incoming.GetClientByCpfUseCase;
import com.fiap.fastfood.application.port.incoming.RegisterClientUseCase;
import com.fiap.fastfood.application.port.outgoing.GetClientByCpfPort;
import com.fiap.fastfood.application.port.outgoing.SaveClientPort;
import com.fiap.fastfood.application.validators.interfaces.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements RegisterClientUseCase, GetClientByCpfUseCase {

    private final SaveClientPort saveClientPort;

    private final GetClientByCpfPort getClientByCpfPort;

    private final ClientValidator clientValidator;

    @Override
    public Client registerClient(Client client) throws AlreadyRegisteredException {
        final var validationResult = clientValidator.validate(client);

        if (!validationResult.getIsValid()) {
            throw new AlreadyRegisteredException(
                    "CLIENT-01",
                    "Couldn't complete registration for client.",
                    validationResult.getErrors()
            );
        }

        return saveClientPort.saveClient(client);
    }

    @Override
    public Client getClientByCpf(String cpf) throws EntityNotFoundException {
        final var client = getClientByCpfPort.getClientByCpf(cpf);

        if (client == null) {
            throw new EntityNotFoundException(
                    "CLIENT-02",
                    "Client not found."
            );
        }

        return client;
    }
}
