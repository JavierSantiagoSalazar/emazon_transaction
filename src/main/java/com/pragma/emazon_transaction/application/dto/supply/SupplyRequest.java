package com.pragma.emazon_transaction.application.dto.supply;

import com.pragma.emazon_transaction.domain.utils.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SupplyRequest {

    @NotNull(message = Constants.ARTICLE_ID_MUST_NOT_BE_NULL)
    @Positive(message = Constants.ARTICLE_ID_MUST_BE_POSITIVE)
    private Integer articleId;

    @NotNull(message = Constants.ARTICLE_AMOUNT_MUST_NOT_BE_NULL)
    @Min(value = 0, message = Constants.ARTICLE_AMOUNT_MUST_BE_POSITIVE)
    private Integer articleAmount;

}
