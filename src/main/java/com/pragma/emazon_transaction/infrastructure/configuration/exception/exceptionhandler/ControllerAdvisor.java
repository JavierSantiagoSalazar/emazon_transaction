package com.pragma.emazon_transaction.infrastructure.configuration.exception.exceptionhandler;


import com.pragma.emazon_transaction.domain.exceptions.ArticleNotFoundException;
import com.pragma.emazon_transaction.domain.exceptions.ArticleRestockDateNotFoundException;
import com.pragma.emazon_transaction.domain.exceptions.ErrorCommunicatingServerException;
import com.pragma.emazon_transaction.domain.exceptions.JwtIsEmptyException;
import com.pragma.emazon_transaction.domain.exceptions.ParsingToJsonException;
import com.pragma.emazon_transaction.domain.exceptions.ParsingToListException;
import com.pragma.emazon_transaction.domain.exceptions.StockServiceUnavailableException;
import com.pragma.emazon_transaction.domain.exceptions.UnauthorizedException;
import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.infrastructure.configuration.exception.dto.Response;
import org.apache.coyote.BadRequestException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.net.ConnectException;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Response> handleArticleNotFoundException(
            ArticleNotFoundException articleNotFoundException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.NOT_FOUND)
                        .message(articleNotFoundException.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handleBadRequestException(
            BadRequestException badRequestException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(badRequestException.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response> handleUnauthorizedException(
            UnauthorizedException unauthorizedException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED)
                        .message(unauthorizedException.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(JwtIsEmptyException.class)
    public ResponseEntity<Response> handleJwtIsEmptyException(
            JwtIsEmptyException jwtIsEmptyException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED)
                        .message(Constants.JWT_IS_EMPTY_ERROR)
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ParsingToJsonException.class)
    public ResponseEntity<Response> handleParsingToJsonException(
            ParsingToJsonException parsingToJsonException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                        .message(parsingToJsonException.getMessage())
                        .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(ParsingToListException.class)
    public ResponseEntity<Response> handleParsingToListException(
            ParsingToListException parsingToListException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY)
                        .message(parsingToListException.getMessage())
                        .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(ErrorCommunicatingServerException.class)
    public ResponseEntity<Response> handleCommunicatingServerException(
            ErrorCommunicatingServerException errorCommunicatingServerException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(Constants.COMMUNICATING_SERVER_ERROR)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Response> handleResponseStatusException(
            ResponseStatusException responseStatusException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(responseStatusException.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(StockServiceUnavailableException.class)
    public ResponseEntity<Response> handleStockServiceUnavailableException(
            StockServiceUnavailableException stockServiceUnavailableException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.SERVICE_UNAVAILABLE)
                        .message(stockServiceUnavailableException.getMessage())
                        .build(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Response> handleConnectException(
            ConnectException connectException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.SERVICE_UNAVAILABLE)
                        .message(connectException.getMessage())
                        .build(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException httpMessageNotReadableException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(httpMessageNotReadableException.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ArticleRestockDateNotFoundException.class)
    public ResponseEntity<Response> handleArticleRestockDateNotFoundException(
            ArticleRestockDateNotFoundException articleRestockDateNotFoundException
    ) {
        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.NOT_FOUND)
                        .message(articleRestockDateNotFoundException.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        List<String> errors = methodArgumentNotValidException.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();


        return new ResponseEntity<>(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .message(errors.toString())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
