package com.pragma.emazon_transaction.domain.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    /* --- SECURITY EXCEPTIONS --- */

    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access: ";

    /* --- OPENAPI CONSTANTS --- */

    public static final String ADD_SUPPLY = "Add supply to stock";
    public static final String SUPPLY_ADDED = "Added the amount of supplies to the stock";

    /* --- OPENAPI CONSTANTS --- */

    public static final String OPEN_API_TITLE = "Emazon Transaction API";
    public static final String OPEN_API_TERMS = "http://swagger.io/terms/";
    public static final String OPEN_API_LICENCE_NAME = "Apache 2.0";
    public static final String OPEN_API_LICENCE_URL = "http://springdoc.org";
    public static final String OPEN_API_APP_DESCRIPTION = "${appDescription}";
    public static final String OPEN_API_APP_VERSION = "${appVersion}";
    public static final String OPEN_API_SWAGGER_UI_HTML = "/swagger-ui/**";
    public static final String OPEN_API_SWAGGER_UI = "/swagger-ui/";
    public static final String OPEN_API_V3_API_DOCS = "/v3/api-docs/**";

    /* --- JWT CONSTANTS --- */

    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String INVALID_TOKEN = "Token invalid, not Authorized";

    /* --- ROLES --- */

    public static final String ROLE_WAREHOUSE_ASSISTANT = "WAREHOUSE_ASSISTANT";

    /* --- SECURITY CONSTANTS ---*/

    public static final String ADD_SUPPLY_URL = "/supply/";

}
