package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Client;
import com.fiap.fastfood.application.exceptions.custom.AlreadyRegisteredException;

public interface RegisterClientUseCase {
    Client registerClient(Client client) throws AlreadyRegisteredException;
}
