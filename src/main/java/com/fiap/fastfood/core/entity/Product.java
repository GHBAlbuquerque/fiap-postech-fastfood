package com.fiap.fastfood.core.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private ProductTypeEnum type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
