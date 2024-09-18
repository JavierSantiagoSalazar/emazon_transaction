package com.pragma.emazon_transaction.application.dto.supply;

import com.pragma.emazon_transaction.domain.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListSupplyRequest {

    @Valid
    @NotNull(message = Constants.DATA_LIST_CANNOT_BE_NULL)
    private List<SupplyRequest> data;

}
