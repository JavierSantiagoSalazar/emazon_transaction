package com.pragma.emazon_transaction.domain.usecase;

import com.pragma.emazon_transaction.domain.exceptions.ArticleRestockDateNotFoundException;
import com.pragma.emazon_transaction.domain.exceptions.ErrorCommunicatingServerException;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.FeignClientPort;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplyUseCaseTest {

    @Mock
    private SupplyPersistencePort defaultSupplyPersistencePort;

    @Mock
    private FeignClientPort feignClientPort;

    @InjectMocks
    private SupplyUseCase defaultSupplyUseCase;

    private Supply defaultSupply;

    @BeforeEach
    void setUp() {
        defaultSupply = new Supply(
                1,
                LocalDate.now(),
                List.of(1, 2, 3),
                List.of(10, 20, 30),
                LocalDate.now()
        );
    }

    @Test
    void givenSuccessfulFeignTransaction_whenAddSupplyToStockIsCalled_thenSupplyIsSaved() {

        when(feignClientPort.updateAmount(defaultSupply)).thenReturn(true);

        defaultSupplyUseCase.addSupplyToStock(defaultSupply);

        verify(feignClientPort, times(1)).updateAmount(defaultSupply);
        verify(defaultSupplyPersistencePort, times(1)).saveSupplyTransaction(defaultSupply);
    }

    @Test
    void givenFailedFeignTransaction_whenAddSupplyToStockIsCalled_thenThrowsErrorCommunicatingServerException() {

        when(feignClientPort.updateAmount(defaultSupply)).thenReturn(false);

        assertThrows(ErrorCommunicatingServerException.class, () -> defaultSupplyUseCase.addSupplyToStock(defaultSupply));

        verify(feignClientPort, times(1)).updateAmount(defaultSupply);
        verify(defaultSupplyPersistencePort, never()).saveSupplyTransaction(defaultSupply);
    }

    @Test
    void givenSupply_whenAddNewRegisterFromStockIsCalled_thenSupplyIsSaved() {

        defaultSupplyUseCase.addNewRegisterFromStock(defaultSupply);

        verify(defaultSupplyPersistencePort, times(1)).saveSupplyTransaction(defaultSupply);
    }

    @Test
    void givenMatchingRestockDates_whenGetRestockDateIsCalled_thenReturnsRestockDates() {

        List<Integer> articleIds = List.of(1, 2, 3);
        List<LocalDate> restockDates = List.of(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)
        );

        when(defaultSupplyPersistencePort.getRestockDate(articleIds)).thenReturn(restockDates);

        List<LocalDate> result = defaultSupplyUseCase.getRestockDate(articleIds);

        assertEquals(restockDates, result);
        verify(defaultSupplyPersistencePort, times(1)).getRestockDate(articleIds);
    }

    @Test
    void givenMismatchedRestockDates_whenGetRestockDateIsCalled_thenThrowsArticleRestockDateNotFoundException() {

        List<Integer> articleIds = List.of(1, 2, 3);
        List<LocalDate> restockDates = List.of(LocalDate.now(), LocalDate.now().plusDays(1));

        when(defaultSupplyPersistencePort.getRestockDate(articleIds)).thenReturn(restockDates);

        assertThrows(ArticleRestockDateNotFoundException.class, () -> defaultSupplyUseCase.getRestockDate(articleIds));

        verify(defaultSupplyPersistencePort, times(1)).getRestockDate(articleIds);
    }

    @Test
    void givenFailedFeignTransaction_whenAddSupplyToStockIsCalled_thenTransactionIsNotSaved() {

        when(feignClientPort.updateAmount(defaultSupply)).thenReturn(false);

        assertThrows(ErrorCommunicatingServerException.class, () -> defaultSupplyUseCase.addSupplyToStock(defaultSupply));

        verify(defaultSupplyPersistencePort, never()).saveSupplyTransaction(defaultSupply);
    }



}
