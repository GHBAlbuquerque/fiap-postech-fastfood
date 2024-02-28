package com.fiap.fastfood.core.usecase;

import com.fiap.fastfood.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.core.entity.Checkout;

import java.util.List;

public class CheckoutUseCaseImpl implements CheckoutUseCase {

    @Override
    public void submit(Checkout checkout, CheckoutGateway checkoutGateway) {
        checkoutGateway.save(checkout);
    }

    @Override
    public List<Checkout> findAll(CheckoutGateway checkoutGateway) {
        return checkoutGateway.findAll();
    }
}
