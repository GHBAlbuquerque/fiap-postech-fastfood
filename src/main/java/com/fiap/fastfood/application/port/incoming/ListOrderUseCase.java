package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Order;

import java.util.List;

public interface ListOrderUseCase {
    List<Order> listOrder();
}
