package com.fiap.fastfood.application.services;

import com.fiap.fastfood.application.domain.Order;
import com.fiap.fastfood.application.port.incoming.CreateOrderUseCase;
import com.fiap.fastfood.application.port.incoming.ListOrderUseCase;
import com.fiap.fastfood.application.port.outgoing.ListOrderPort;
import com.fiap.fastfood.application.port.outgoing.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase, ListOrderUseCase {

    private final SaveOrderPort saveOrderPort;
    private final ListOrderPort listOrderPort;

    @Override
    public void createOrder(Order order) {
        saveOrderPort.saveOrder(order);
    }

    @Override
    public List<Order> listOrder() {
        return listOrderPort.listOrder();
    }
}
