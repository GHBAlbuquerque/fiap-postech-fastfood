package com.fiap.fastfood.adapters.web.exceptions;

import com.fiap.fastfood.application.exceptions.custom.AlreadyRegisteredException;
import com.fiap.fastfood.application.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.application.exceptions.model.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    //TODO: ADICIONAR EXCEÇÕES CUSTOMIZADAS QUE FOREM FEITAS


    @ExceptionHandler(value = {AlreadyRegisteredException.class})
    public ResponseEntity<ExceptionDetails> resourceException(AlreadyRegisteredException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400",
                "The request could not be completed due to a conflict.",
                ex.getCode(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getErrors());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ExceptionDetails> resourceException(EntityNotFoundException ex, WebRequest request) {

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404",
                "The requested resource was not found.",
                ex.getCode(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getErrors());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtException(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        final var message = new ExceptionDetails(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500",
                "Internal server error. Please contact the admin.",
                ex.getClass().toString(),
                ex.getMessage(),
                status.value(),
                new Date(),
                null);

        ex.printStackTrace();

        return handleExceptionInternal(ex, message, null, status, request);
    }

}
