package com.fiap.fastfood.common.builders;

import com.fiap.fastfood.common.dto.request.RegisterClientRequest;
import com.fiap.fastfood.common.dto.response.GetClientResponse;
import com.fiap.fastfood.core.entity.Client;
import com.fiap.fastfood.external.orm.ClientORM;

import java.time.LocalDateTime;

public class ClientBuilder {

    public static Client fromRequestToDomain(RegisterClientRequest request) {
        return (Client) new Client()
                .setName(request.getName())
                .setBirthday(request.getBirthday())
                .setCpf(request.getCpf())
                .setCreationTimestamp(LocalDateTime.now())
                .setEmail(request.getEmail())
                .setPassword(request.getPassword());
    }

    public static GetClientResponse fromDomainToResponse(Client client) {
        return new GetClientResponse()
                .setId(client.getId())
                .setName(client.getName())
                .setBirthday(client.getBirthday())
                .setCpf(client.getCpf())
                .setEmail(client.getEmail())
                .setCreationTimestamp(client.getCreationTimestamp())
                .setUpdateTimestamp(client.getUpdateTimestamp());
    }

    public static Client fromOrmToDomain(ClientORM orm) {
        return (Client) new Client()
                .setId(orm.getId())
                .setName(orm.getName())
                .setBirthday(orm.getBirthday())
                .setCpf(orm.getCpf())
                .setCreationTimestamp(LocalDateTime.now())
                .setEmail(orm.getEmail())
                .setPassword(orm.getPassword());
    }

    public static ClientORM fromDomainToOrm(Client client) {
        return (ClientORM) new ClientORM()
                .setId(client.getId())
                .setName(client.getName())
                .setBirthday(client.getBirthday())
                .setCpf(client.getCpf())
                .setEmail(client.getEmail())
                .setPassword(client.getPassword())
                .setCreationTimestamp(client.getCreationTimestamp())
                .setUpdateTimestamp(client.getUpdateTimestamp());
    }
}
