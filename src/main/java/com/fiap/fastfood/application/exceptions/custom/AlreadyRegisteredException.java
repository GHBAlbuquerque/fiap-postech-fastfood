package com.fiap.fastfood.application.exceptions.custom;

import com.fiap.fastfood.application.exceptions.model.CustomError;
import com.fiap.fastfood.application.exceptions.model.CustomException;

import java.util.List;

public class AlreadyRegisteredException extends CustomException {

    public AlreadyRegisteredException(String code, String message) {
        super(code, message);
    }

    public AlreadyRegisteredException(String code, String message, List<CustomError> customErrors) {
        super(code, message, customErrors);
    }
}