package com.fiap.fastfood.application.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {

    private String message;
    private String field;
    private Object attemptedValue;

}