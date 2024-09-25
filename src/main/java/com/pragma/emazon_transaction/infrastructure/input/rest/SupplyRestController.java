package com.pragma.emazon_transaction.infrastructure.input.rest;

import com.pragma.emazon_transaction.application.dto.supply.ListSupplyRequest;
import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;
import com.pragma.emazon_transaction.application.handler.supply.SupplyHandler;
import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.domain.utils.HttpStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/supply")
@RequiredArgsConstructor
public class SupplyRestController {

    private final SupplyHandler supplyHandler;

    @Operation(summary = Constants.ADD_SUPPLY,
            description = Constants.ADD_SUPPLY_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.OK,
                    description = Constants.SUPPLY_ADDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
                    description = Constants.COMMUNICATING_SERVER_ERROR,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.NOT_FOUND,
                    description = Constants.RESOURCE_NOT_FOUND_ERROR,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.INVALID_REQUEST_ERROR,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.UNAUTHORIZED,
                    description = Constants.UNAUTHORIZED_ERROR,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
                    description = Constants.INTERNAL_SERVER_ERROR,
                    content = @Content
            )
    })
    @PostMapping("/")
    public ResponseEntity<Void> addSupplyToStock(@Valid @RequestBody ListSupplyRequest listSupplyRequest) {
        supplyHandler.addSupplyToStock(listSupplyRequest.getData());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = Constants.ADD_NEW_STOCK_REGISTER)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.OK,
                    description = Constants.STOCK_REGISTER_ADDED,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.INVALID_REQUEST,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
                    description = Constants.UNEXPECTED_ERROR,
                    content = @Content
            )
    })
    @PostMapping("/add-new-register")
    public ResponseEntity<Void> addNewRegisterFromStock(@Valid @RequestBody SupplyRequest supplyRequest) {
        supplyHandler.addNewRegisterFromStock(supplyRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = Constants.GET_RE_STOCK_DATES)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.OK,
                    description = Constants.RESTOCK_DATES_FOUND,
                    content = @Content(array = @ArraySchema(schema = @Schema(type = "string", format = "date")))
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.NOT_FOUND,
                    description = Constants.ARTICLE_NOT_FOUND,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpStatusCode.BAD_REQUEST,
                    description = Constants.INVALID_REQUEST,
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<LocalDate>> getRestockDate(@RequestParam List<Integer> articleIdList) {
        return ResponseEntity.ok(supplyHandler.getRestockDate(articleIdList));
    }

}
