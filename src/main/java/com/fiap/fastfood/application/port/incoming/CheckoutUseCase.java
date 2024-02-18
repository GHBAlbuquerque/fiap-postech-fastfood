package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Checkout;

import java.util.List;

public interface CheckoutUseCase {
    void submit(Checkout checkout);

    List<Checkout> findAll();
}
