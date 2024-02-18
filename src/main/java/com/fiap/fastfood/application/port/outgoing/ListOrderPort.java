package com.fiap.fastfood.application.port.outgoing;

import com.fiap.fastfood.application.domain.Order;

import java.util.List;

public interface ListOrderPort {
    List<Order> listOrder();
}
