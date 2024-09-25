package com.pragma.emazon_transaction.domain.usecase;

import com.pragma.emazon_transaction.domain.api.SupplyServicePort;
import com.pragma.emazon_transaction.domain.exceptions.ArticleRestockDateNotFoundException;
import com.pragma.emazon_transaction.domain.exceptions.ErrorCommunicatingServerException;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.FeignClientPort;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import com.pragma.emazon_transaction.domain.utils.Constants;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public void addNewRegisterFromStock(Supply supply) {
        supplyPersistencePort.saveSupplyTransaction(supply);
    }

    @Override
    public List<LocalDate> getRestockDate(List<Integer> articleIdList) {

        List<LocalDate> restockDates = supplyPersistencePort.getRestockDate(articleIdList);

        if (articleIdList.size() != restockDates.size()) {
            throw new ArticleRestockDateNotFoundException(Constants.SUPPLY_NO_RESTOCKING_DATE);
        }

        return restockDates;
    }

}
