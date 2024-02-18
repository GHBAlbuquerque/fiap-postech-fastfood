package com.fiap.fastfood.application.validators.interfaces;

import com.fiap.fastfood.application.validators.ValidationResult;

public interface CustomValidator<T> {
    ValidationResult validate(T object);
}
