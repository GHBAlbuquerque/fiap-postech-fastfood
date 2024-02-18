package com.fiap.fastfood.application.validators.impls;

import com.fiap.fastfood.application.domain.Client;
import com.fiap.fastfood.application.exceptions.model.CustomError;
import com.fiap.fastfood.application.port.outgoing.GetClientByCpfPort;
import com.fiap.fastfood.application.validators.ValidationResult;
import com.fiap.fastfood.application.validators.interfaces.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidatorImpl implements ClientValidator {

    private final GetClientByCpfPort getClientByCpfPort;

    @Override
    public ValidationResult validate(Client client) {
        final var validationResult = new ValidationResult();
        final var cpf = client.getCpf();

        if (validateCpfInUse(cpf)) {
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


    private boolean validateCpfInUse(String cpf) {
        final var clientUsingCpf = getClientByCpfPort.getClientByCpf(cpf);

        return clientUsingCpf != null;
    }
}

