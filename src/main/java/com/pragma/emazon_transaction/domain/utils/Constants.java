package com.pragma.emazon_transaction.domain.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    /* --- FEIGN CONSTANTS --- */

    public static final String STOCK_MICROSERVICE_NAME = "emazon-stock";

    /* --- FEIGN EXCEPTIONS --- */

    public static final String COMMUNICATING_SERVER_ERROR = "Error trying to communicate the server";
    public static final String RESOURCE_NOT_FOUND_ERROR = "Article not found: ";
    public static final String INVALID_REQUEST_ERROR = "Invalid request to the endpoint: ";
    public static final String UNAUTHORIZED_ERROR = "Unauthorized for the resource: ";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String ERROR_PROCESSING_RESPONSE_BODY = "Error processing response body";
    public static final String STOCK_SERVICE_UNAVAILABLE = "The StockService is unavailable";

    /* --- MAPSTRUCT CONSTANTS --- */

    public static final String SPRING_COMPONENT_MODEL = "spring";

    /* --- SECURITY EXCEPTIONS --- */

    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access or expired token: ";

    /* --- VALIDATION CONSTANTS: ARTICLE --- */

    public static final String DATA_LIST_CANNOT_BE_NULL = "The data send cannot be null";
    public static final String ARTICLE_ID_MUST_NOT_BE_NULL = "Article ID cannot be null";
    public static final String ARTICLE_ID_MUST_BE_POSITIVE = "Article ID must be a positive number";
    public static final String ARTICLE_AMOUNT_MUST_NOT_BE_NULL = "Article amount cannot be null";
    public static final String ARTICLE_AMOUNT_MUST_BE_POSITIVE = "Article amount must be a positive number";

    /* --- OPENAPI CONSTANTS --- */

    public static final String ADD_SUPPLY = "Add supply to stock";
    public static final String ADD_SUPPLY_DESCRIPTION = "Add a the new amount of supplies to the articles";
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
    public static final String CLAIM_EXPIRATION = "exp";
    public static final String INVALID_TOKEN = "Token invalid, not Authorized";
    public static final String JWT_IS_EMPTY_ERROR = "The JWT is empty";
    public static final String TOKEN_HAS_EXPIRED = "Token has expired";

    /* --- ROLES --- */

    public static final String ROLE_WAREHOUSE_ASSISTANT = "WAREHOUSE_ASSISTANT";

    /* --- SECURITY CONSTANTS ---*/

    public static final String ADD_SUPPLY_URL = "/supply/";

}
