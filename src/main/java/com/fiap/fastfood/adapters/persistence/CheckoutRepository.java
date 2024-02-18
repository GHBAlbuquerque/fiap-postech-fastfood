package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.builders.CheckoutBuilder;
import com.fiap.fastfood.application.domain.Checkout;
import com.fiap.fastfood.application.port.outgoing.CheckoutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class CheckoutRepository implements CheckoutPort {

    private final SpringDataMongoCheckoutRepository springDataMongoCheckoutRepository;

    @Override
    public void save(Checkout checkout) {
        final var orm = CheckoutBuilder.fromDomainToOrm(checkout);
        springDataMongoCheckoutRepository.save(orm);
    }

    @Override
    public List<Checkout> findAll() {
        final var orms = springDataMongoCheckoutRepository.findAllByOrderByCreatedAtAsc();
        final var checkouts = orms.stream().map(orm -> CheckoutBuilder.fromOrmToDomain(orm)).collect(Collectors.toList());
        return checkouts;
    }
}
