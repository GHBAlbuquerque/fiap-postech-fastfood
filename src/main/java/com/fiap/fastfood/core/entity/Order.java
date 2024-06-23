package com.fiap.fastfood.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;

    private String clientId;

    private List<Item> items;

    private BigDecimal totalValue;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private OrderStatus status;

    private OrderPaymentStatus paymentStatus;

    public BigDecimal getTotalValue() {
        return items.stream()
                .map(Item::getTotalItemValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
