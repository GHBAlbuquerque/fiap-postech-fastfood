package com.fiap.fastfood.common.interfaces.datasources;

import com.fiap.fastfood.external.orm.ProductORM;
import com.fiap.fastfood.external.orm.ProductTypeEnumORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoProductRepository extends MongoRepository<ProductORM, String> {

    List<ProductORM> findByType(ProductTypeEnumORM type);
}
