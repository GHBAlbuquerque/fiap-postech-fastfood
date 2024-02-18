package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.persistence.orm.ProductORM;
import com.fiap.fastfood.adapters.persistence.orm.ProductTypeEnumORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoProductRepository extends MongoRepository<ProductORM, String> {

    List<ProductORM> findByType(ProductTypeEnumORM type);
}
