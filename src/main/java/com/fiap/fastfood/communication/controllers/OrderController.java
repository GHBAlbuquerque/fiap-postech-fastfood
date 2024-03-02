package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.OrderBuilder;
import com.fiap.fastfood.common.dto.request.CreateOrderRequest;
import com.fiap.fastfood.common.dto.response.GetOrderResponse;
import com.fiap.fastfood.common.dto.response.GetOrderPaymentStatusResponse;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.OrderGateway;
import com.fiap.fastfood.common.interfaces.usecase.OrderUseCase;
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

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest request) {
        useCase.createOrder(OrderBuilder.fromRequestToDomain(request), gateway);
    }

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getOrders() {
        final var result = useCase.listOrder(gateway);

        return ResponseEntity.ok(result.stream()
                .map(OrderBuilder::fromDomainToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{orderId}/payment-status")
    public ResponseEntity<GetOrderPaymentStatusResponse> getOrderPaymentStatus(@PathVariable String orderId) throws EntityNotFoundException {
        return ResponseEntity.ok(GetOrderPaymentStatusResponse.builder()
                .paymentStatus(useCase.getOrderById(orderId, gateway).getPaymentStatus())
                .build());
    }

}
