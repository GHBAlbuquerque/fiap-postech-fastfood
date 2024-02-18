package com.fiap.fastfood.application.services;

import com.fiap.fastfood.application.domain.Checkout;
import com.fiap.fastfood.application.port.incoming.CheckoutUseCase;
import com.fiap.fastfood.application.port.outgoing.CheckoutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService implements CheckoutUseCase {

    private final CheckoutPort checkoutPort;

    @Override
    public void submit(Checkout checkout) {
        checkoutPort.save(checkout);
    }

    @Override
    public List<Checkout> findAll() {
        return checkoutPort.findAll();
    }
}
