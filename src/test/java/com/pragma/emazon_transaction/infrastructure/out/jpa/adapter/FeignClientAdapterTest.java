package com.pragma.emazon_transaction.infrastructure.out.jpa.adapter;

import com.pragma.emazon_transaction.application.dto.supply.SupplyStockRequest;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.infrastructure.feing.StockFeignClient;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyStockRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FeignClientAdapterTest {

    @Mock
    private StockFeignClient stockFeignClient;
    @Mock
    private SupplyStockRequestMapper supplyStockRequestMapper;

    @InjectMocks
    private FeignClientAdapter feignClientAdapter;

    private Supply defaultSupply;
    private SupplyStockRequest defaultSupplyStockRequest;

    @BeforeEach
    public void setUp() {

        defaultSupply = new Supply(1, LocalDate.EPOCH, List.of(10, 5), List.of(100, 600));
        defaultSupplyStockRequest = new SupplyStockRequest();
        defaultSupplyStockRequest.setSupplyArticleIds(List.of(10, 5));
        defaultSupplyStockRequest.setSupplyArticleAmounts(List.of(100, 600));
    }

    @Test
    void givenValidSupply_whenUpdateAmountIsCalled_thenAmountIsUpdated() {

        when(supplyStockRequestMapper.toRequest(defaultSupply)).thenReturn(defaultSupplyStockRequest);
        when(stockFeignClient.updateAmount(defaultSupplyStockRequest)).thenReturn(ResponseEntity.ok(true));

        Boolean result = feignClientAdapter.updateAmount(defaultSupply);

        verify(supplyStockRequestMapper, times(1)).toRequest(defaultSupply);
        verify(stockFeignClient, times(1)).updateAmount(defaultSupplyStockRequest);
        assertTrue(result);
    }

    @Test
    void givenInvalidSupply_whenUpdateAmountIsCalled_thenReturnsFalse() {

        when(supplyStockRequestMapper.toRequest(defaultSupply)).thenReturn(defaultSupplyStockRequest);
        when(stockFeignClient.updateAmount(defaultSupplyStockRequest))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false));

        Boolean result = feignClientAdapter.updateAmount(defaultSupply);

        verify(supplyStockRequestMapper, times(1)).toRequest(defaultSupply);
        verify(stockFeignClient, times(1)).updateAmount(defaultSupplyStockRequest);
        assertFalse(result);
    }
}

