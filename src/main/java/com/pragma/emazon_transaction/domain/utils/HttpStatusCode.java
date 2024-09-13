package com.pragma.emazon_transaction.domain.utils;

import lombok.Getter;

@Getter
public class HttpStatusCode {

    private HttpStatusCode() {
        throw new IllegalStateException("Utility class");
    }

    public static final String OK = "200";
    public static final String CREATED = "201";

}
