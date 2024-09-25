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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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
                List.of(10, 20),
                LocalDate.now()
        );

        when(supplyRequestMapper.toDomain(supplyRequests)).thenReturn(mappedSupply);

        supplyHandlerImpl.addSupplyToStock(supplyRequests);

        verify(supplyRequestMapper, times(1)).toDomain(supplyRequests);

        verify(supplyServicePort, times(1)).addSupplyToStock(mappedSupply);
    }

    @Test
    void givenValidSupplyRequest_whenAddNewRegisterFromStock_thenSupplyIsMappedAndSaved() {

        SupplyRequest supplyRequest = new SupplyRequest(1, 10);
        Supply mappedSupply = new Supply(
                1,
                LocalDate.now(),
                List.of(1),
                List.of(10),
                LocalDate.now()
        );

        when(supplyRequestMapper.toDomain(supplyRequest)).thenReturn(mappedSupply);

        supplyHandlerImpl.addNewRegisterFromStock(supplyRequest);

        verify(supplyRequestMapper, times(1)).toDomain(supplyRequest);
        verify(supplyServicePort, times(1)).addNewRegisterFromStock(mappedSupply);
    }

    @Test
    void givenArticleIdList_whenGetRestockDate_thenReturnsRestockDates() {

        List<Integer> articleIdList = List.of(1, 2, 3);
        List<LocalDate> expectedRestockDates = List.of(LocalDate.now(), LocalDate.now().plusDays(1));

        when(supplyServicePort.getRestockDate(articleIdList)).thenReturn(expectedRestockDates);

        List<LocalDate> restockDates = supplyHandlerImpl.getRestockDate(articleIdList);

        assertEquals(expectedRestockDates, restockDates);
        verify(supplyServicePort, times(1)).getRestockDate(articleIdList);
    }

    @Test
    void givenEmptyArticleIdList_whenGetRestockDate_thenReturnsEmptyList() {

        List<Integer> emptyArticleIdList = Collections.emptyList();
        List<LocalDate> expectedRestockDates = Collections.emptyList();

        when(supplyServicePort.getRestockDate(emptyArticleIdList)).thenReturn(expectedRestockDates);

        List<LocalDate> restockDates = supplyHandlerImpl.getRestockDate(emptyArticleIdList);

        assertTrue(restockDates.isEmpty());
        verify(supplyServicePort, times(1)).getRestockDate(emptyArticleIdList);
    }

    @Test
    void givenInvalidSupplyRequest_whenAddNewRegisterFromStock_thenThrowsException() {

        SupplyRequest invalidSupplyRequest = new SupplyRequest(null, -10); // Datos inválidos

        when(supplyRequestMapper.toDomain(invalidSupplyRequest)).thenThrow(new IllegalArgumentException("Invalid data"));

        assertThrows(IllegalArgumentException.class, () -> {
            supplyHandlerImpl.addNewRegisterFromStock(invalidSupplyRequest);
        });

        verify(supplyRequestMapper, times(1)).toDomain(invalidSupplyRequest);
        verify(supplyServicePort, never()).addNewRegisterFromStock(any(Supply.class));
    }

}
