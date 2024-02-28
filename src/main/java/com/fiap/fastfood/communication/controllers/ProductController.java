package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.ProductBuilder;
import com.fiap.fastfood.common.dto.request.BaseProductResponse;
import com.fiap.fastfood.common.dto.request.CreateProductRequest;
import com.fiap.fastfood.common.dto.request.UpdateProductRequest;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductGateway gateway;
    private final ProductUseCase useCase;

    public ProductController(ProductGateway productGateway, ProductUseCase productUseCase) {
        this.gateway = productGateway;
        this.useCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<BaseProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        final var productReq = ProductBuilder.toDomain(request);

        final var product = useCase.createProduct(productReq, gateway);

        return ResponseEntity.created(URI.create(product.getId())).body(ProductBuilder.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody UpdateProductRequest request
    ) throws EntityNotFoundException {

        final var productReq = ProductBuilder.toDomain(request, LocalDateTime.now());

        final var newProduct = useCase.updateProduct(id, productReq, gateway);

        return ResponseEntity.ok(ProductBuilder.toResponse(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        useCase.deleteProduct(id, gateway);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BaseProductResponse>> findProduct(
            @RequestParam ProductTypeEnum category
    ) {
        final var result = useCase.findByType(category, gateway);

        return ResponseEntity.ok(
                result.stream().map(
                        ProductBuilder::toResponse
                ).collect(Collectors.toList())
        );
    }

}
