package com.pragma.emazon_transaction.infrastructure.out.jpa.adapter;

import com.pragma.emazon_transaction.application.dto.supply.SupplyStockRequest;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.FeignClientPort;
import com.pragma.emazon_transaction.infrastructure.feing.StockFeignClient;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyStockRequestMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignClientAdapter implements FeignClientPort {

    private final StockFeignClient stockFeignClient;
    private final SupplyStockRequestMapper supplyStockRequestMapper;

    @Override
    public Boolean updateAmount(Supply supply) {
        SupplyStockRequest supplyStockRequest = supplyStockRequestMapper.toRequest(supply);
        return stockFeignClient.updateAmount(supplyStockRequest).getBody();
    }
}
