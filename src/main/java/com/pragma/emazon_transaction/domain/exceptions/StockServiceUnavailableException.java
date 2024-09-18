package com.pragma.emazon_transaction.domain.exceptions;

public class StockServiceUnavailableException extends RuntimeException {
    public StockServiceUnavailableException(String message) {
        super(message);
    }
}
