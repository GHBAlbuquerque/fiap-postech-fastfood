package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Client;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;

public interface GetClientByCpfUseCase {
    Client getClientByCpf(String cpf) throws EntityNotFoundException;
}
