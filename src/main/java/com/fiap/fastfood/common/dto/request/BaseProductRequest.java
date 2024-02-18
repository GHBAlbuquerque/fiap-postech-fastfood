package com.fiap.fastfood.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseProductRequest {

    @NotBlank(message = "The name cannot be blank")
    @Size(min = 3, message = "The name must have at least 3 letters")
    String name;

    @NotNull(message = "The price must be a not null")
    @Positive(message = "The price must be a positive value")
    BigDecimal price;

    @NotBlank(message = "Description cannot be blank")
    String description;

    @NotBlank(message = "The type cannot be blank")
    String type;
}
