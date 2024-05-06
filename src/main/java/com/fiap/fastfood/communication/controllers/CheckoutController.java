package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.CheckoutBuilder;
import com.fiap.fastfood.common.dto.request.CheckoutRequest;
import com.fiap.fastfood.common.dto.response.CheckoutResponse;
import com.fiap.fastfood.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.common.interfaces.usecase.CheckoutUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutGateway gateway;
    private final CheckoutUseCase useCase;

    public CheckoutController(CheckoutGateway checkoutGateway, CheckoutUseCase checkoutUseCase) {
        this.gateway = checkoutGateway;
        this.useCase = checkoutUseCase;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public void checkout(@RequestBody CheckoutRequest request) {
        final var checkoutReq = CheckoutBuilder.fromRequestToDomain(request);

        useCase.submit(checkoutReq, gateway);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<CheckoutResponse>> findAll() {
        final var result = useCase.findAll(gateway);

        return ResponseEntity.ok(result.stream()
                .map(CheckoutBuilder::fromDomainToResponse)
                .collect(Collectors.toList()));
    }
}
