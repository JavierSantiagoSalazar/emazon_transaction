package com.pragma.emazon_transaction.domain.utils;

import lombok.Getter;

@Getter
public class HttpStatusCode {

    private HttpStatusCode() {
        throw new IllegalStateException("Utility class");
    }

    public static final String OK = "200";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String UNAUTHORIZED = "301";
    public static final String NOT_FOUND = "404";
    public static final String BAD_REQUEST = "400";

}
