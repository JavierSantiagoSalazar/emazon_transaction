package com.pragma.emazon_transaction.application.handler.supply;

import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;
import com.pragma.emazon_transaction.application.mappers.supply.SupplyRequestMapper;
import com.pragma.emazon_transaction.domain.api.SupplyServicePort;
import com.pragma.emazon_transaction.domain.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplyHandlerImplTest {

    @Mock
    private SupplyServicePort supplyServicePort;

    @Mock
    private SupplyRequestMapper supplyRequestMapper;

    @InjectMocks
    private SupplyHandlerImpl supplyHandlerImpl;

    private List<SupplyRequest> supplyRequests;

    @BeforeEach
    void setUp() {

        SupplyRequest supplyRequest1 = new SupplyRequest(1, 10);
        SupplyRequest supplyRequest2 = new SupplyRequest(2, 20);

        supplyRequests = List.of(supplyRequest1, supplyRequest2);
    }

    @Test
    void givenValidSupplyRequests_whenAddSupplyToStock_thenSupplyIsMappedAndSaved() {

        Supply mappedSupply = new Supply(
                1,
                LocalDate.now(),
                List.of(1, 2),
                List.of(10, 20)
        );

        when(supplyRequestMapper.toDomain(supplyRequests)).thenReturn(mappedSupply);

        supplyHandlerImpl.addSupplyToStock(supplyRequests);

        verify(supplyRequestMapper, times(1)).toDomain(supplyRequests);

        verify(supplyServicePort, times(1)).addSupplyToStock(mappedSupply);
    }

}
