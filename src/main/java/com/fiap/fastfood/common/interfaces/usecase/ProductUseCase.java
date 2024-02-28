package com.fiap.fastfood.common.interfaces.usecase;

import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;

import java.util.List;

public interface ProductUseCase {

    Product createProduct(Product product, ProductGateway productGateway);

    Product updateProduct(String id, Product product, ProductGateway productGateway) throws EntityNotFoundException;

    void deleteProduct(String id, ProductGateway productGateway);

    List<Product> findByType(ProductTypeEnum type, ProductGateway productGateway);


}
