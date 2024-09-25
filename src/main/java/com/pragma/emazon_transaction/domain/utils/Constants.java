package com.pragma.emazon_transaction.domain.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SUPPLY_NO_RESTOCKING_DATE = "No restocking date found for some items.";


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

    public static final String ADD_NEW_STOCK_REGISTER = "Add New Stock Register";
    public static final String STOCK_REGISTER_ADDED = "Stock register added successfully";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred";


    public static final String GET_RE_STOCK_DATES = "Get Restock Dates";
    public static final String RESTOCK_DATES_FOUND = "Restock dates found successfully";
    public static final String ARTICLE_NOT_FOUND = "Article not found";

    /* --- MAPSTRUCT CONSTANTS --- */

    public static final String SPRING_COMPONENT_MODEL = "spring";
    public static final Short DAYS_TO_RESTOCK = 15;

    /* --- SECURITY EXCEPTIONS --- */

    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access or expired token: ";

    /* --- VALIDATION CONSTANTS: ARTICLE --- */

    public static final String DATA_LIST_CANNOT_BE_NULL = "The data send cannot be null";
    public static final String ARTICLE_ID_MUST_NOT_BE_NULL = "Article ID cannot be null";
    public static final String ARTICLE_ID_MUST_BE_POSITIVE = "Article ID must be a positive number";
    public static final String ARTICLE_AMOUNT_MUST_NOT_BE_NULL = "Article amount cannot be null";
    public static final String ARTICLE_AMOUNT_MUST_BE_POSITIVE = "Article amount must be a positive number or zero";

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
    public static final String INVALID_TOKEN = "Token invalid, not Authorized";
    public static final String JWT_IS_EMPTY_ERROR = "The JWT is empty";

    /* --- ROLES --- */

    public static final String ROLE_WAREHOUSE_ASSISTANT = "WAREHOUSE_ASSISTANT";
    public static final String ROLE_ADMIN = "ADMIN";

    /* --- SECURITY CONSTANTS ---*/

    public static final String ADD_SUPPLY_URL = "/supply/";
    public static final String ADD_NEW_REGISTER_URL = "/supply/add-new-register";

}
