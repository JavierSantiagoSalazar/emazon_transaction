package com.pragma.emazon_transaction.domain.spi;

import com.pragma.emazon_transaction.domain.model.Supply;

public interface SupplyPersistencePort {

    void saveSupplyTransaction(Supply supply);

}
