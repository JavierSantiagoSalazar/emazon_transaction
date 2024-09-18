package com.pragma.emazon_transaction.infrastructure.feing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon_transaction.domain.exceptions.ArticleNotFoundException;
import com.pragma.emazon_transaction.domain.exceptions.StockServiceUnavailableException;
import com.pragma.emazon_transaction.domain.exceptions.UnauthorizedException;
import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.infrastructure.configuration.exception.dto.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        if (status == HttpStatus.NOT_FOUND) {
            try {
                String responseBody = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
                ObjectMapper objectMapper = new ObjectMapper();

                Response errorResponse = objectMapper.readValue(responseBody, Response.class);

                return new ArticleNotFoundException(errorResponse.getMessage());
            } catch (IOException e) {
                return new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        Constants.ERROR_PROCESSING_RESPONSE_BODY
                );
            }
        }

        return switch (status) {
            case BAD_REQUEST -> new BadRequestException(Constants.INVALID_REQUEST_ERROR + methodKey);
            case UNAUTHORIZED -> new UnauthorizedException(Constants.UNAUTHORIZED_ERROR + methodKey);
            case SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT ->
                    new StockServiceUnavailableException(Constants.STOCK_SERVICE_UNAVAILABLE + methodKey);
            case INTERNAL_SERVER_ERROR ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR);
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}