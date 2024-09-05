package com.transactions.supplies.domain.exceptions;

public class InvalidExistantSupply extends RuntimeException{
    public InvalidExistantSupply(String message) {
        super(message);
    }
}
