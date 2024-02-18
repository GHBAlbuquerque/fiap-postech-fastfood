package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.persistence.orm.OrderORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoOrderRepository extends MongoRepository<OrderORM, String> {

}
