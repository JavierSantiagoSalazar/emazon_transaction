package com.pragma.emazon_transaction.domain.exceptions;

public class ParsingToJsonException extends RuntimeException{
    public ParsingToJsonException(String message) {
        super(message);
    }
}
