package com.fiap.fastfood.application.port.incoming;

import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;

import java.util.List;

public interface ProductUseCase {

    Product createProduct(Product product);

    Product updateProduct(String id, Product product) throws EntityNotFoundException;

    void deleteProduct(String id);

    List<Product> findByType(ProductTypeEnum type);


}
