package com.pragma.emazon_transaction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Supply {

    private Integer supplyId;
    private LocalDate supplyDate;
    private List<Integer> supplyArticleIds;
    private List<Integer> supplyArticleAmounts;

}
