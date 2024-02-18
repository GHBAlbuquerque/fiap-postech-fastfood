package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.builders.OrderBuilder;
import com.fiap.fastfood.application.domain.Order;
import com.fiap.fastfood.application.port.outgoing.ListOrderPort;
import com.fiap.fastfood.application.port.outgoing.SaveOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderRepository implements SaveOrderPort, ListOrderPort {

    private final SpringDataMongoOrderRepository repository;

    @Override
    public void saveOrder(Order order) {
        final var orm = OrderBuilder.fromDomainToOrm(order);
        repository.save(orm);
    }

    @Override
    public List<Order> listOrder() {
        final var orms = repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return orms.stream().map(OrderBuilder::fromOrmToDomain).collect(Collectors.toList());
    }
}
