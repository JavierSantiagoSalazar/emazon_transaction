package com.pragma.emazon_transaction.application.handler.supply;


import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;
import com.pragma.emazon_transaction.application.mappers.supply.SupplyRequestMapper;
import com.pragma.emazon_transaction.domain.api.SupplyServicePort;
import com.pragma.emazon_transaction.domain.model.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SupplyHandlerImpl implements SupplyHandler {

    private final SupplyServicePort supplyServicePort;
    private final SupplyRequestMapper supplyRequestMapper;

    @Override
    public void addSupplyToStock(List<SupplyRequest> supplyRequests) {
        Supply supply = supplyRequestMapper.toDomain(supplyRequests);
        supplyServicePort.addSupplyToStock(supply);
    }

    @Override
    public void addNewRegisterFromStock(SupplyRequest supplyRequest) {
        Supply supply = supplyRequestMapper.toDomain(supplyRequest);
        supplyServicePort.addNewRegisterFromStock(supply);
    }

    @Override
    public List<LocalDate> getRestockDate(List<Integer> articleIdList) {
        return supplyServicePort.getRestockDate(articleIdList);
    }

}
