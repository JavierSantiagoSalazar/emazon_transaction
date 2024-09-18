package com.pragma.emazon_transaction.domain.usecase;

import com.pragma.emazon_transaction.domain.api.SupplyServicePort;
import com.pragma.emazon_transaction.domain.exceptions.ErrorCommunicatingServerException;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.FeignClientPort;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SupplyUseCase implements SupplyServicePort {

    private final SupplyPersistencePort supplyPersistencePort;
    private final FeignClientPort feignClientPort;

    @Override
    public void addSupplyToStock(Supply supply) {

        boolean isTransactionSuccessful = feignClientPort.updateAmount(supply);

        if (!isTransactionSuccessful){
            throw new ErrorCommunicatingServerException();
        }

        supplyPersistencePort.saveSupplyTransaction(supply);
    }

}
