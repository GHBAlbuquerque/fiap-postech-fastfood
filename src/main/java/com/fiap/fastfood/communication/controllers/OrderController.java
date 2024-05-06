package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.OrderBuilder;
import com.fiap.fastfood.common.dto.request.CreateOrderRequest;
import com.fiap.fastfood.common.dto.response.GetOrderResponse;
import com.fiap.fastfood.common.dto.response.GetOrderPaymentStatusResponse;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.OrderGateway;
import com.fiap.fastfood.common.interfaces.usecase.OrderUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderGateway gateway;
    private final OrderUseCase useCase;

    public OrderController(OrderGateway orderGateway, OrderUseCase orderUseCase) {
        this.gateway = orderGateway;
        this.useCase = orderUseCase;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(produces="application/json", consumes="application/json")
    public void createOrder(@RequestBody CreateOrderRequest request) {
        useCase.createOrder(OrderBuilder.fromRequestToDomain(request), gateway);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<List<GetOrderResponse>> getOrders() {
        final var result = useCase.listOrder(gateway);

        return ResponseEntity.ok(result.stream()
                .map(OrderBuilder::fromDomainToResponse)
                .collect(Collectors.toList()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value="/{orderId}/payment-status", produces="application/json", consumes="application/json")
    public ResponseEntity<GetOrderPaymentStatusResponse> getOrderPaymentStatus(@PathVariable String orderId) throws EntityNotFoundException {
        return ResponseEntity.ok(GetOrderPaymentStatusResponse.builder()
                .paymentStatus(useCase.getOrderById(orderId, gateway).getPaymentStatus())
                .build());
    }

}
