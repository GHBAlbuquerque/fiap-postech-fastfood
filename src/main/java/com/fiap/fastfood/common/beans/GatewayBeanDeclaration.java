package com.fiap.fastfood.common.beans;

import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoCheckoutRepository;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoClientRepository;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoOrderRepository;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoProductRepository;
import com.fiap.fastfood.common.interfaces.gateways.*;
import com.fiap.fastfood.communication.gateways.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
public class GatewayBeanDeclaration {

    @Bean
    public CheckoutGateway checkoutGateway(SpringDataMongoCheckoutRepository repository) {
        return new CheckoutGatewayImpl(repository);
    }

    @Bean
    public ClientGateway clientGateway(SpringDataMongoClientRepository repository) {
        return new ClientGatewayImpl(repository);
    }

    @Bean
    public OrderGateway orderGateway(SpringDataMongoOrderRepository repository) {
        return new OrderGatewayImpl(repository);
    }

    @Bean
    public ProductGateway productGateway(SpringDataMongoProductRepository repository) {
        return new ProductGatewayImpl(repository);
    }

    @Bean
    public AuthenticationGateway authenticationGateway(CognitoIdentityProviderClient cognitoIdentityProviderClient) {
        return new AuthenticationGatewayImpl(cognitoIdentityProviderClient);
    }
}
