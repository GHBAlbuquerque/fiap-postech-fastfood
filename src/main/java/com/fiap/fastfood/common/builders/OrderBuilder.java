package com.fiap.fastfood.common.builders;

import com.fiap.fastfood.common.dto.request.CreateOrderRequest;
import com.fiap.fastfood.common.dto.response.GetOrderResponse;
import com.fiap.fastfood.core.entity.Order;
import com.fiap.fastfood.external.orm.OrderORM;

import java.util.stream.Collectors;

public class OrderBuilder {

    public static GetOrderResponse fromDomainToResponse(Order order) {
        return GetOrderResponse.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .totalValue(order.getTotalValue())
                .items(order.getItems())
                .build();
    }

    public static Order fromRequestToDomain(CreateOrderRequest request) {
        return Order.builder()
                .items(request.getItems())
                .build();
    }

    public static Order fromOrmToDomain(OrderORM orm) {
        return Order.builder()
                .id(orm.getId())
                .createdAt(orm.getCreatedAt())
                .updatedAt(orm.getUpdatedAt())
                .totalValue(orm.getTotalValue())
                .items(
                        orm.getItems().stream().map(ItemBuilder::fromOrmToDomain).collect(Collectors.toList())
                )
                .build();
    }

    public static OrderORM fromDomainToOrm(Order order) {
        return OrderORM.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .totalValue(order.getTotalValue())
                .items(
                        order.getItems().stream().map(ItemBuilder::fromDomainToOrm).collect(Collectors.toList())
                )
                .build();
    }
}
