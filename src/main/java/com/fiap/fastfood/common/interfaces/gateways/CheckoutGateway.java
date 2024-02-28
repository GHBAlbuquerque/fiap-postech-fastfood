package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.core.entity.Checkout;

import java.util.List;

public interface CheckoutGateway {

    void save(Checkout checkout);

    List<Checkout> findAll();
}
