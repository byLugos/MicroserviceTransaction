package com.transactions.supplies.domain.exceptions;
public class InvalidSupplyName extends RuntimeException{
    public InvalidSupplyName(String message) {
        super(message);
    }
}
