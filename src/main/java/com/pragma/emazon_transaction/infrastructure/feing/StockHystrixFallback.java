package com.pragma.emazon_transaction.infrastructure.feing;

import com.pragma.emazon_transaction.application.dto.supply.SupplyStockRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StockHystrixFallback implements StockFeignClient {

    @Override
    public ResponseEntity<Boolean> updateAmount(SupplyStockRequest supplyStockRequest) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
}
