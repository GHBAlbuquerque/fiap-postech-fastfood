package com.fiap.fastfood.external.orm;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@Document
public class ProductORM {

    @MongoId
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    @Indexed
    private ProductTypeEnumORM type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
