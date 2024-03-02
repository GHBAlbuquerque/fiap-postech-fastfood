package com.fiap.fastfood.common.dto.response;

import com.fiap.fastfood.core.entity.OrderPaymentStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderPaymentStatusResponse {
    private OrderPaymentStatus paymentStatus;
}
