package com.fiap.fastfood.common.interfaces.usecase;

import com.fiap.fastfood.common.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.core.entity.Client;

public interface ClientUseCase {
    Client getClientByCpf(String cpf, ClientGateway clientGateway) throws EntityNotFoundException;

    Client registerClient(Client client, ClientGateway clientGateway) throws AlreadyRegisteredException;

    Boolean validateCpfInUse(String cpf, ClientGateway clientGateway);
}
