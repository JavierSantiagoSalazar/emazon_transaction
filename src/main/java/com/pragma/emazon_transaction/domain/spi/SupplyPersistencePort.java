package com.pragma.emazon_transaction.domain.spi;

import com.pragma.emazon_transaction.domain.model.Supply;

import java.time.LocalDate;
import java.util.List;

public interface SupplyPersistencePort {

    void saveSupplyTransaction(Supply supply);

    List<LocalDate> getRestockDate(List<Integer> articleIdList);

}
