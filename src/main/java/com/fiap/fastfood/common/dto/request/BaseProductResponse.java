package com.fiap.fastfood.common.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseProductResponse {

    String name;
    BigDecimal price;
    String description;
    String type;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
