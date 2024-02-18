package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.builders.ClientBuilder;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoClientRepository;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.core.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientGatewayImpl implements ClientGateway {

    private final SpringDataMongoClientRepository repository;

    public ClientGatewayImpl(SpringDataMongoClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client saveClient(Client client) {
        final var orm = ClientBuilder.fromDomainToOrm(client);
        final var result = repository.save(orm);
        return ClientBuilder.fromOrmToDomain(result);
    }

    @Override
    public Client getClientByCpf(String cpf) {
        final var result = repository.findByCpf(cpf);
        if (result != null) return ClientBuilder.fromOrmToDomain(result);

        return null;
    }
}
