package com.pragma.emazon_transaction.domain.exceptions;

public class ArticleRestockDateNotFoundException extends RuntimeException {
    public ArticleRestockDateNotFoundException(String message) {
        super(message);
    }
}
