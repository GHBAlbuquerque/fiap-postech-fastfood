package com.fiap.fastfood.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

    private String id;
    private String orderId;
    private String status;
    private LocalDateTime createdAt;
}
