package com.fiap.fastfood.adapters.builders;

import com.fiap.fastfood.adapters.persistence.orm.ProductORM;
import com.fiap.fastfood.adapters.persistence.orm.ProductTypeEnumORM;
import com.fiap.fastfood.adapters.web.request.BaseProductResponse;
import com.fiap.fastfood.adapters.web.request.CreateProductRequest;
import com.fiap.fastfood.adapters.web.request.UpdateProductRequest;
import com.fiap.fastfood.application.domain.Product;
import com.fiap.fastfood.application.domain.ProductTypeEnum;

import java.time.LocalDateTime;

public class ProductBuilder {

    public static Product toDomain(CreateProductRequest request) {
        return buildCreateProductFromRequest(request);
    }

    public static Product toDomain(UpdateProductRequest request, LocalDateTime timestamp) {
        return buildUpdateProductFromRequest(request, LocalDateTime.now());
    }

    public static BaseProductResponse toResponse(Product product) {
        return BaseProductResponse
                .builder()
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType().toString())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private static <BaseProductRequest> Product buildCreateProductFromRequest(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .type(ProductTypeEnum.valueOf(request.getType()))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }

    private static <BaseProductRequest> Product buildUpdateProductFromRequest(UpdateProductRequest request, LocalDateTime updatedAt) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .type(ProductTypeEnum.valueOf(request.getType()))
                .updatedAt(updatedAt)
                .build();
    }

    public static Product fromOrmToDomain(ProductORM orm) {
        return Product.builder()
                .id(orm.getId())
                .name(orm.getName())
                .price(orm.getPrice())
                .description(orm.getDescription())
                .type(ProductTypeEnum.valueOf(
                        orm.getType().toString())
                )
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }

    public static ProductORM fromDomainToOrm(Product product) {
        return ProductORM.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .type(ProductTypeEnumORM.valueOf(
                        product.getType().toString())
                )
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }

    public static ProductORM fromORMtoNewORM(ProductORM orm) {
        return ProductORM.builder()
                .id(orm.getId())
                .name(orm.getName())
                .price(orm.getPrice())
                .description(orm.getDescription())
                .type(ProductTypeEnumORM.valueOf(
                        orm.getType().toString())
                )
                .createdAt(orm.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
