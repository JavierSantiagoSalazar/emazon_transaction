package com.pragma.emazon_transaction.application.dto.supply;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SupplyStockRequest {

    private List<Integer> supplyArticleIds;
    private List<Integer> supplyArticleAmounts;

}
