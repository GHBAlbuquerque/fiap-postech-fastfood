package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.builders.ProductBuilder;
import com.fiap.fastfood.common.interfaces.datasources.SpringDataMongoProductRepository;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import com.fiap.fastfood.external.orm.ProductTypeEnumORM;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGatewayImpl implements ProductGateway {

    private final SpringDataMongoProductRepository repository;

    public ProductGatewayImpl(SpringDataMongoProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteProduct(String id) {
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
