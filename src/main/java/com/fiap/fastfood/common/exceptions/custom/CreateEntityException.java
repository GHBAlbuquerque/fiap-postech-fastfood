package com.fiap.fastfood.common.exceptions.custom;

import com.fiap.fastfood.common.exceptions.model.CustomError;
import com.fiap.fastfood.common.exceptions.model.CustomException;

import java.util.List;

public class CreateEntityException extends CustomException {

    public CreateEntityException(String code, String message) {
        super(code, message);
    }

    public CreateEntityException(String code, String message, List<CustomError> customErrors) {
        super(code, message, customErrors);
    }
}