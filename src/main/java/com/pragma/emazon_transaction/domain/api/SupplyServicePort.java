package com.pragma.emazon_transaction.domain.api;

import com.pragma.emazon_transaction.domain.model.Supply;

public interface SupplyServicePort {

    void addSupplyToStock(Supply supply);

}
