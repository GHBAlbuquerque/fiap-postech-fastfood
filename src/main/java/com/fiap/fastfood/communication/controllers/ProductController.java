package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.builders.ProductBuilder;
import com.fiap.fastfood.common.dto.request.BaseProductResponse;
import com.fiap.fastfood.common.dto.request.CreateProductRequest;
import com.fiap.fastfood.common.dto.request.UpdateProductRequest;
import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.exceptions.model.ExceptionDetails;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PostMapping(produces="application/json", consumes="application/json")
    public ResponseEntity<BaseProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        final var productReq = ProductBuilder.toDomain(request);

        final var product = useCase.createProduct(productReq, gateway);

        return ResponseEntity.created(URI.create(product.getId())).body(ProductBuilder.toResponse(product));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @PutMapping(value="/{id}", produces="application/json", consumes="application/json")
    public ResponseEntity<BaseProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody UpdateProductRequest request
    ) throws EntityNotFoundException {

        final var productReq = ProductBuilder.toDomain(request, LocalDateTime.now());

        final var newProduct = useCase.updateProduct(id, productReq, gateway);

        return ResponseEntity.ok(ProductBuilder.toResponse(newProduct));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @DeleteMapping(value = "/{id}", produces="application/json", consumes="application/json")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        useCase.deleteProduct(id, gateway);

        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class)))
    })
    @GetMapping(produces="application/json", consumes="application/json")
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
