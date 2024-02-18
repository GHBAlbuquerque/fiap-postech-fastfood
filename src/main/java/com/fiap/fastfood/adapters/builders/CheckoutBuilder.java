package com.fiap.fastfood.adapters.builders;

import com.fiap.fastfood.adapters.persistence.orm.CheckoutORM;
import com.fiap.fastfood.adapters.web.request.CheckoutRequest;
import com.fiap.fastfood.adapters.web.response.CheckoutResponse;
import com.fiap.fastfood.application.domain.Checkout;

public class CheckoutBuilder {

    public static Checkout fromRequestToDomain(CheckoutRequest request) {
        return Checkout.builder()
                .status("In Progress")
                .orderId(request.getOrderId())
                .build();
    }

    public static CheckoutResponse fromDomainToResponse(Checkout checkout) {
        return CheckoutResponse.builder()
                .id(checkout.getId())
                .status(checkout.getStatus())
                .orderId(checkout.getOrderId())
                .createAt(checkout.getCreatedAt())
                .build();
    }

    public static Checkout fromOrmToDomain(CheckoutORM orm) {
        return Checkout.builder()
                .id(orm.getId())
                .status(orm.getStatus())
                .orderId(orm.getOrderId())
                .build();
    }

    public static CheckoutORM fromDomainToOrm(Checkout checkout) {
        return CheckoutORM.builder()
                .id(checkout.getId())
                .status(checkout.getStatus())
                .orderId(checkout.getOrderId())
                .createdAt(checkout.getCreatedAt())
                .build();
    }
}
