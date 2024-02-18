package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.builders.ClientBuilder;
import com.fiap.fastfood.application.domain.Client;
import com.fiap.fastfood.application.port.outgoing.GetClientByCpfPort;
import com.fiap.fastfood.application.port.outgoing.SaveClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientRepository implements SaveClientPort, GetClientByCpfPort {

    private final SpringDataMongoClientRepository repository;

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
