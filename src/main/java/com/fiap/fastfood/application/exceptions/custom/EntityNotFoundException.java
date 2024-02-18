package com.fiap.fastfood.application.exceptions.custom;

import com.fiap.fastfood.application.exceptions.model.CustomError;
import com.fiap.fastfood.application.exceptions.model.CustomException;

import java.util.List;

public class EntityNotFoundException extends CustomException {

    public EntityNotFoundException(String code, String message) {
        super(code, message);
    }

    public EntityNotFoundException(String code, String message, List<CustomError> customErrors) {
        super(code, message, customErrors);
    }
}
