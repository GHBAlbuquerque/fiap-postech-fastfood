package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.builders.OrderBuilder;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoOrderRepository;
import com.fiap.fastfood.common.interfaces.gateways.OrderGateway;
import com.fiap.fastfood.core.entity.Order;
import com.fiap.fastfood.core.entity.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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
    public Order getOrderById(String id) throws EntityNotFoundException {
        return repository.findById(id)
                .map(OrderBuilder::fromOrmToDomain)
                .orElseThrow(() -> new EntityNotFoundException("ORDER-01", String.format("Order with ID %s not found", id)));
    }

    @Override
    public List<Order> listOrder() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")).stream()
                .filter(order -> order.getStatus() != OrderStatus.COMPLETED)
                .map(OrderBuilder::fromOrmToDomain)
                .sorted(Comparator.comparing(order -> getOrderStatusPriority(order.getStatus())))
                .collect(Collectors.toList());
    }

    private int getOrderStatusPriority(OrderStatus status) {
        switch (status) {
            case READY:
                return 1;
            case IN_PREPARATION:
                return 2;
            case RECEIVED:
                return 3;
            default:
                return 0;
        }
    }
}
