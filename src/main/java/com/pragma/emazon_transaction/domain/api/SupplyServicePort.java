package com.pragma.emazon_transaction.domain.api;

import com.pragma.emazon_transaction.domain.model.Supply;

import java.time.LocalDate;
import java.util.List;

public interface SupplyServicePort {

    void addSupplyToStock(Supply supply);

    void addNewRegisterFromStock(Supply supply);

    List<LocalDate> getRestockDate(List<Integer> articleIdList);

}
