package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.core.entity.Client;

public interface ClientGateway {

    Client getClientByCpf(String cpf);

    Client saveClient(Client client);

}
