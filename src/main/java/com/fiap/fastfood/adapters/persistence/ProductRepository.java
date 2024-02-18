package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.builders.ProductBuilder;
import com.fiap.fastfood.adapters.persistence.orm.ProductTypeEnumORM;
import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;
import com.fiap.fastfood.application.port.outgoing.DeleteProductPort;
import com.fiap.fastfood.application.port.outgoing.FindByTypePort;
import com.fiap.fastfood.application.port.outgoing.SaveProductPort;
import com.fiap.fastfood.application.port.outgoing.UpdateProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class ProductRepository implements SaveProductPort, DeleteProductPort, UpdateProductPort, FindByTypePort {

    @Autowired
    SpringDataMongoProductRepository repository;

    @Override
    public void deleteProductPort(String id) {
        repository.deleteById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        final var orm = ProductBuilder.fromDomainToOrm(product);
        final var result = repository.save(orm);
        return ProductBuilder.fromOrmToDomain(result);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        final var existsProduct = repository.findById(id);

        if (existsProduct.isEmpty()) return null;

        final var updatedProduct = ProductBuilder.fromORMtoNewORM(existsProduct.get());

        final var savedProduct = repository.save(updatedProduct);

        return ProductBuilder.fromOrmToDomain(savedProduct);
    }

    @Override
    public List<Product> findByType(ProductTypeEnum type) {
        final var productType = ProductTypeEnumORM.valueOf(type.toString());

        final var orms = repository.findByType(productType);

        return orms.stream().map(ProductBuilder::fromOrmToDomain).collect(Collectors.toList());
    }
}
