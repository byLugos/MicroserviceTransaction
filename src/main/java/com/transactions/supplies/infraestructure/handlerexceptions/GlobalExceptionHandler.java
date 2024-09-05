package com.transactions.supplies.infraestructure.handlerexceptions;

import com.transactions.supplies.domain.exceptions.InvalidExistantSupply;
import com.transactions.supplies.domain.exceptions.InvalidSupplyName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSupplyName.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidSupplyName ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "SUMINISTRO_INVÁLIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidExistantSupply.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidExistantSupply(InvalidExistantSupply ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "SUMINISTRO_EXISTENTE");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
