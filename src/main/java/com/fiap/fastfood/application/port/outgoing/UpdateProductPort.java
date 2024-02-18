package com.fiap.fastfood.application.port.outgoing;

import com.fiap.fastfood.application.domain.Product;

public interface UpdateProductPort {
    Product updateProduct(String id, Product product);
}
