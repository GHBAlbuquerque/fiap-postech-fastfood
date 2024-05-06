package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.ClientBuilder;
import com.fiap.fastfood.common.dto.request.RegisterClientRequest;
import com.fiap.fastfood.common.dto.response.GetClientResponse;
import com.fiap.fastfood.common.dto.response.RegisterClientResponse;
import com.fiap.fastfood.common.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.common.interfaces.usecase.ClientUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientGateway gateway;
    private final ClientUseCase useCase;

    public ClientController(ClientGateway clientGateway, ClientUseCase clientUseCase) {
        this.gateway = clientGateway;
        this.useCase = clientUseCase;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<RegisterClientResponse> registerClient(
            @RequestBody RegisterClientRequest request
    ) throws AlreadyRegisteredException {

        final var clientReq = ClientBuilder.fromRequestToDomain(request);
        final var client = useCase.registerClient(clientReq, gateway);

        final var uri = URI.create(client.getId());

        return ResponseEntity.created(uri).body(new RegisterClientResponse(client.getId()));

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<GetClientResponse> getClientByCpf(@RequestParam(required = true) String cpf)
            throws EntityNotFoundException {

        final var client = useCase.getClientByCpf(cpf, gateway);
        final var clientResponse = ClientBuilder.fromDomainToResponse(client);

        return ResponseEntity.ok(clientResponse);
    }
}
