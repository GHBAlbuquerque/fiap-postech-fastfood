package com.fiap.fastfood.common.interfaces.datasources;

import com.fiap.fastfood.external.orm.OrderORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoOrderRepository extends MongoRepository<OrderORM, String> {

}
