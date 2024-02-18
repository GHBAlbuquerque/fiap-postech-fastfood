package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.persistence.orm.CheckoutORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoCheckoutRepository extends MongoRepository<CheckoutORM, String> {
    List<CheckoutORM> findAllByOrderByCreatedAtAsc();
}
