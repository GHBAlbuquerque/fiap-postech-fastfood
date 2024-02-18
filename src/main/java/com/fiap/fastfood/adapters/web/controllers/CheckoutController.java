package com.fiap.fastfood.adapters.web.controllers;

import com.fiap.fastfood.adapters.builders.CheckoutBuilder;
import com.fiap.fastfood.adapters.web.request.CheckoutRequest;
import com.fiap.fastfood.adapters.web.response.CheckoutResponse;
import com.fiap.fastfood.application.port.incoming.CheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutUseCase checkoutUseCase;

    @PostMapping
    public void checkout(@RequestBody CheckoutRequest request) {
        checkoutUseCase.submit(CheckoutBuilder.fromRequestToDomain(request));
    }

    @GetMapping
    public ResponseEntity<List<CheckoutResponse>> findAll() {
        return ResponseEntity.ok(checkoutUseCase.findAll().stream()
                .map(CheckoutBuilder::fromDomainToResponse)
                .collect(Collectors.toList()));
    }
}
