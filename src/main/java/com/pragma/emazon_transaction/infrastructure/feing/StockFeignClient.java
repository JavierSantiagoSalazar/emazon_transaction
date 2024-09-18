package com.pragma.emazon_transaction.infrastructure.feing;

import com.pragma.emazon_transaction.application.dto.supply.SupplyStockRequest;
import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.infrastructure.configuration.bean.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = Constants.STOCK_MICROSERVICE_NAME,
        url = "${emazon-stock.url}",
        configuration = ClientConfiguration.class,
        fallback = StockHystrixFallback.class
)
public interface StockFeignClient {

    @PatchMapping(value = "/")
    ResponseEntity<Boolean> updateAmount(@RequestBody SupplyStockRequest supplyStockRequest);

}
