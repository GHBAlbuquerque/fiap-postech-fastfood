package com.fiap.fastfood.application.port.outgoing;

import com.fiap.fastfood.application.domain.Checkout;

import java.util.List;

public interface CheckoutPort {

    void save(Checkout checkout);

    List<Checkout> findAll();
}
