package com.fiap.fastfood.adapters.web.controllers;

import com.fiap.fastfood.adapters.builders.ProductBuilder;
import com.fiap.fastfood.adapters.web.request.BaseProductResponse;
import com.fiap.fastfood.adapters.web.request.CreateProductRequest;
import com.fiap.fastfood.adapters.web.request.UpdateProductRequest;
import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.application.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;


    @PostMapping
    public ResponseEntity<BaseProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(ProductBuilder.toDomain(request));
        return ResponseEntity.created(URI.create(product.getId())).body(ProductBuilder.toResponse(product));
    }


    @PutMapping("/{id}")
    public ResponseEntity<BaseProductResponse> updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request) throws EntityNotFoundException {
        Product newProduct = productService.updateProduct(id, ProductBuilder.toDomain(request, LocalDateTime.now()));
        return ResponseEntity.ok(ProductBuilder.toResponse(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BaseProductResponse>> findProduct(@RequestParam ProductTypeEnum category) {
        return ResponseEntity.ok(
                productService.findByType(category).stream().map(
                        ProductBuilder::toResponse
                ).collect(Collectors.toList())
        );
    }

}
