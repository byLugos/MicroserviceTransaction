package com.transactions.supplies.domain.exceptions;

public class InvalidRequest extends RuntimeException{
    public InvalidRequest(String message) {
        super(message);
    }
}
