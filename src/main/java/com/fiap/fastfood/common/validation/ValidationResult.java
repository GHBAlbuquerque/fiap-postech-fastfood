package com.fiap.fastfood.common.validation;

import com.fiap.fastfood.common.exceptions.model.CustomError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {

    private Boolean isValid = true;
    private List<CustomError> errors = new ArrayList<>();

}