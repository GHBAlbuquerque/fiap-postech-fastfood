package com.fiap.fastfood.application.services;

import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.application.port.incoming.ProductUseCase;
import com.fiap.fastfood.application.port.outgoing.DeleteProductPort;
import com.fiap.fastfood.application.port.outgoing.FindByTypePort;
import com.fiap.fastfood.application.port.outgoing.SaveProductPort;
import com.fiap.fastfood.application.port.outgoing.UpdateProductPort;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private final SaveProductPort saveProduct;

    private final DeleteProductPort deleteProduct;

    private final UpdateProductPort updateProduct;

    private final FindByTypePort findByTypePort;

    @Override
    public Product createProduct(Product product) {
        final Product savedProduct = saveProduct.saveProduct(product);
        logger.info("created product with success id = {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public Product updateProduct(String id, Product product) throws EntityNotFoundException {

        final var newProduct = updateProduct.updateProduct(id, product);

        if (newProduct == null) {
            logger.error("not found product with id = {}", id);
            throw new EntityNotFoundException(
                    product.getId(),
                    "Product not found."
            );
        }

        logger.info("product updated successfully = {}", newProduct.getId());
        return newProduct;
    }

    @Override
    public void deleteProduct(String id) {
        deleteProduct.deleteProductPort(id);
        logger.info("product deleted successfully = {}", id);
    }

    @Override
    public List<Product> findByType(ProductTypeEnum type) {
        return findByTypePort.findByType(type);
    }


}
