package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Order;

public interface CreateOrderUseCase {
    void createOrder(Order order);
}
