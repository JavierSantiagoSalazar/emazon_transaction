package com.pragma.emazon_transaction.domain.exceptions;

public class JwtIsEmptyException extends RuntimeException{
    public JwtIsEmptyException(String message) {
        super(message);
    }
}
