package com.pragma.emazon_transaction.infrastructure.input.rest;

import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.domain.utils.HttpStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/supply")
@RequiredArgsConstructor
public class SupplyRestController {

    @Operation(summary = Constants.ADD_SUPPLY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HttpStatusCode.OK,
                    description = Constants.SUPPLY_ADDED,
                    content = @Content
            )
    })
    @PatchMapping("/")
    public String addSupplyToStock() {
        return "Added to stock";
    }

}
