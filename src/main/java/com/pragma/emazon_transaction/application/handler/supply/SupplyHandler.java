package com.pragma.emazon_transaction.application.handler.supply;

import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;

import java.util.List;

public interface SupplyHandler {

    void addSupplyToStock(List<SupplyRequest> supplyRequests);

}
