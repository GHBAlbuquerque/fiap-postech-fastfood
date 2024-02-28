package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;

import java.util.List;

public interface ProductGateway {
    void deleteProduct(String id);

    List<Product> findByType(ProductTypeEnum type);

    Product saveProduct(Product product);

    Product updateProduct(String id, Product product);
}
