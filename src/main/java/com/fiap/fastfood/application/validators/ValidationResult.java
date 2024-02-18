package com.fiap.fastfood.application.validators;

import com.fiap.fastfood.application.exceptions.model.CustomError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {

    private Boolean isValid = true;
    private List<CustomError> errors = new ArrayList<>();

}
