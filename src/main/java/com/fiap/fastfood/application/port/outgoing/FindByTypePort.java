package com.fiap.fastfood.application.port.outgoing;

import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;

import java.util.List;

public interface FindByTypePort {
    List<Product> findByType(ProductTypeEnum type);
}
