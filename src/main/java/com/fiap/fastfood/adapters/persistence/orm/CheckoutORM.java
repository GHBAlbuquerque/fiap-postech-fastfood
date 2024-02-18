package com.fiap.fastfood.adapters.persistence.orm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CheckoutORM {

    @MongoId
    private String id;
    private String orderId;
    private String status;
    @CreatedDate
    private LocalDateTime createdAt;
}
