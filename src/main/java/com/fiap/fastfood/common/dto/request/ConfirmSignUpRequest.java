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
public class ConfirmSignUpRequest {

    @NotBlank(message = "The cpf cannot be blank")
    String cpf;

    @NotBlank(message = "The code must be a not null")
    String code;

}
