package com.fiap.fastfood.core.entity;

import com.fiap.fastfood.common.exceptions.model.CustomError;
import com.fiap.fastfood.common.validation.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Person {

    private String id;

    public static ValidationResult validate(Client client, Boolean cpfInUse) {
        final var validationResult = new ValidationResult();
        final var cpf = client.getCpf();

        if (cpfInUse) {
            validationResult.getErrors().add(
                    new CustomError(
                            "CPF document number informed for registration is already in use.",
                            "cpf",
                            cpf));
        }

        if (!validationResult.getErrors().isEmpty())
            validationResult.setIsValid(false);

        return validationResult;
    }

}
