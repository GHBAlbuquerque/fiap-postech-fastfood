package com.fiap.fastfood.common.beans;

import com.fiap.fastfood.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.common.interfaces.usecase.ClientUseCase;
import com.fiap.fastfood.common.interfaces.usecase.OrderUseCase;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.usecase.CheckoutUseCaseImpl;
import com.fiap.fastfood.core.usecase.ClientUseCaseImpl;
import com.fiap.fastfood.core.usecase.OrderUseCaseImpl;
import com.fiap.fastfood.core.usecase.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanDeclaration {

    @Bean
    public CheckoutUseCase checkoutUseCase() {
        return new CheckoutUseCaseImpl();
    }

    @Bean
    public ClientUseCase clientUseCase() {
        return new ClientUseCaseImpl();
    }

    @Bean
    public OrderUseCase orderUseCase() {
        return new OrderUseCaseImpl();
    }

    @Bean
    public ProductUseCase productUseCase() {
        return new ProductUseCaseImpl();
    }
}
