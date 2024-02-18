package com.fiap.fastfood.adapters.web.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CheckoutResponse {
    private String id;
    private String orderId;
    private String status;
    private LocalDateTime createAt;
}
