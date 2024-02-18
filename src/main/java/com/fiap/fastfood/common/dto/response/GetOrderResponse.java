package com.fiap.fastfood.common.dto.response;

import com.fiap.fastfood.core.entity.Item;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponse {
    private String id;
    private List<Item> items;
    private BigDecimal totalValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
