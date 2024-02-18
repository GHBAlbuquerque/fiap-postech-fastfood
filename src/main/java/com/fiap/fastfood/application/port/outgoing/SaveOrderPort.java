package com.fiap.fastfood.application.port.outgoing;

import com.fiap.fastfood.application.domain.Order;

public interface SaveOrderPort {
    void saveOrder(Order order);
}
