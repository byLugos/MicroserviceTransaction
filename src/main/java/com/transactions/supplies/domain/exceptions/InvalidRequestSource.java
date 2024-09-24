package com.transactions.supplies.domain.exceptions;

public class InvalidRequestSource extends RuntimeException{
    public InvalidRequestSource(String message) {
        super(message);
    }
}
