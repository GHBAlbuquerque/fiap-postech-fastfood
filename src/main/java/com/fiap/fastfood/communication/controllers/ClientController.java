package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.ClientBuilder;
import com.fiap.fastfood.common.dto.request.ConfirmSignUpRequest;
import com.fiap.fastfood.common.dto.request.RegisterClientRequest;
import com.fiap.fastfood.common.dto.response.GetClientResponse;
import com.fiap.fastfood.common.dto.response.RegisterClientResponse;
import com.fiap.fastfood.common.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.exceptions.custom.IdentityProviderRegistrationException;
import com.fiap.fastfood.common.exceptions.model.ExceptionDetails;
import com.fiap.fastfood.common.interfaces.gateways.AuthenticationGateway;
import com.fiap.fastfood.common.interfaces.gateways.ClientGateway;
import com.fiap.fastfood.common.interfaces.usecase.ClientUseCase;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final AuthenticationGateway authenticationGateway;
    private final ClientGateway clientGateway;
    private final ClientUseCase useCase;

    public ClientController(AuthenticationGateway authenticationGateway, ClientGateway clientGateway, ClientUseCase clientUseCase) {
        this.authenticationGateway = authenticationGateway;
        this.clientGateway = clientGateway;
        this.useCase = clientUseCase;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<RegisterClientResponse> registerClient(
            @RequestBody RegisterClientRequest request
    ) throws AlreadyRegisteredException, IdentityProviderRegistrationException {

        final var clientReq = ClientBuilder.fromRequestToDomain(request);
        final var client = useCase.registerClient(clientReq, clientGateway, authenticationGateway);

        final var uri = URI.create(client.getId());

        return ResponseEntity.created(uri).body(new RegisterClientResponse(client.getId()));

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<GetClientResponse> getClientByCpf(@RequestParam(required = true) String cpf)
            throws EntityNotFoundException {

        final var client = useCase.getClientByCpf(cpf, clientGateway);
        final var clientResponse = ClientBuilder.fromDomainToResponse(client);

        return ResponseEntity.ok(clientResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PostMapping(value = "/confirmation", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Boolean> confirmSignUp(@RequestBody(required = true) ConfirmSignUpRequest confirmSignUpRequest)
            throws IdentityProviderRegistrationException {

        final var response = useCase.confirmClientSignUp(confirmSignUpRequest.getCpf(),
                confirmSignUpRequest.getCode(),
                authenticationGateway);

        return ResponseEntity.ok(response);
    }
}
