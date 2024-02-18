package com.fiap.fastfood.adapters.web.response;

import com.fiap.fastfood.application.domain.Item;
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
