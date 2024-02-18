package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.builders.OrderBuilder;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoOrderRepository;
import com.fiap.fastfood.common.interfaces.gateways.OrderGateway;
import com.fiap.fastfood.core.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderGatewayImpl implements OrderGateway {

    private final SpringDataMongoOrderRepository repository;

    public OrderGatewayImpl(SpringDataMongoOrderRepository repository) {
        this.repository = repository;
    }

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
