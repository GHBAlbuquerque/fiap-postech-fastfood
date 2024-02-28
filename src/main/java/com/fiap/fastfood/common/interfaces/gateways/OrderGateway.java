package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.core.entity.Order;

import java.util.List;

public interface OrderGateway {

    List<Order> listOrder();

    void saveOrder(Order order);

}
