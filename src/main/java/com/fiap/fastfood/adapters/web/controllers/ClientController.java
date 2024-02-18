package com.fiap.fastfood.adapters.web.controllers;


import com.fiap.fastfood.adapters.builders.ClientBuilder;
import com.fiap.fastfood.adapters.web.request.RegisterClientRequest;
import com.fiap.fastfood.adapters.web.response.GetClientResponse;
import com.fiap.fastfood.adapters.web.response.RegisterClientResponse;
import com.fiap.fastfood.application.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.application.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<RegisterClientResponse> registerClient(@RequestBody RegisterClientRequest request)
            throws AlreadyRegisteredException {

        final var clientReq = ClientBuilder.fromRequestToDomain(request);
        final var client = clientService.registerClient(clientReq);

        final var uri = URI.create(client.getId());
        return ResponseEntity.created(uri).body(new RegisterClientResponse(client.getId()));

    }

    @GetMapping
    public ResponseEntity<GetClientResponse> getClientByCpf(@RequestParam(required = true) String cpf)
            throws EntityNotFoundException {

        final var client = clientService.getClientByCpf(cpf);
        final var clientResponse = ClientBuilder.fromDomainToResponse(client);

        return ResponseEntity.ok(clientResponse);
    }
}
