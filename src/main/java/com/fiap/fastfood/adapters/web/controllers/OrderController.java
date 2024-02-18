package com.fiap.fastfood.adapters.web.controllers;


import com.fiap.fastfood.adapters.builders.OrderBuilder;
import com.fiap.fastfood.adapters.web.request.CreateOrderRequest;
import com.fiap.fastfood.adapters.web.response.GetOrderResponse;
import com.fiap.fastfood.application.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(OrderBuilder.fromRequestToDomain(request));
    }

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.listOrder().stream()
                .map(OrderBuilder::fromDomainToResponse)
                .collect(Collectors.toList()));
    }
}
