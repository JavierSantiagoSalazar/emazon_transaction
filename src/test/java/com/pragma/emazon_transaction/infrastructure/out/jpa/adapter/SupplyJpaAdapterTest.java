package com.pragma.emazon_transaction.infrastructure.out.jpa.adapter;

import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.infrastructure.out.jpa.entity.SupplyEntity;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyEntityMapper;
import com.pragma.emazon_transaction.infrastructure.out.jpa.repository.SupplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplyJpaAdapterTest {

    @Mock
    private SupplyRepository supplyRepository;
    @Mock
    private SupplyEntityMapper supplyEntityMapper;

    @InjectMocks
    private SupplyJpaAdapter supplyJpaAdapter;

    private Supply defaultSupply;
    private SupplyEntity defaultSupplyEntity;

    @BeforeEach
    public void setUp() {

        defaultSupply = new Supply(1, LocalDate.EPOCH, List.of(10, 5), List.of(100, 600), LocalDate.now());
        defaultSupplyEntity = new SupplyEntity(
                1,
                LocalDate.EPOCH,
                List.of(10, 5),
                List.of(100, 600),
                LocalDate.now()
        );
    }

    @Test
    void givenSupply_whenSaveSupplyTransactionIsCalled_thenSupplyIsSaved() {

        when(supplyEntityMapper.toEntity(defaultSupply)).thenReturn(defaultSupplyEntity);

        supplyJpaAdapter.saveSupplyTransaction(defaultSupply);

        verify(supplyRepository, times(1)).save(defaultSupplyEntity);
        verify(supplyEntityMapper, times(1)).toEntity(defaultSupply);
    }

    @Test
    void givenValidArticleIds_whenGetRestockDateIsCalled_thenReturnsRestockDates() {

        List<Integer> articleIds = List.of(1, 2);
        List<LocalDate> expectedRestockDates = List.of(LocalDate.now(), LocalDate.now().plusDays(1));

        when(supplyRepository.findLatestRestockDateForArticle("1")).thenReturn(LocalDate.now());
        when(supplyRepository.findLatestRestockDateForArticle("2"))
                .thenReturn(LocalDate.now().plusDays(1));

        List<LocalDate> restockDates = supplyJpaAdapter.getRestockDate(articleIds);

        assertEquals(expectedRestockDates, restockDates);
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("1");
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("2");
    }

    @Test
    void givenSomeInvalidArticleIds_whenGetRestockDateIsCalled_thenReturnsPartialRestockDates() {

        List<Integer> articleIds = List.of(1, 2, 3);
        List<LocalDate> expectedRestockDates = List.of(LocalDate.now(), LocalDate.now().plusDays(1));

        when(supplyRepository.findLatestRestockDateForArticle("1")).thenReturn(LocalDate.now());
        when(supplyRepository.findLatestRestockDateForArticle("2")).
                thenReturn(LocalDate.now().plusDays(1));
        when(supplyRepository.findLatestRestockDateForArticle("3")).thenReturn(null);

        List<LocalDate> restockDates = supplyJpaAdapter.getRestockDate(articleIds);

        assertEquals(expectedRestockDates, restockDates);
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("1");
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("2");
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("3");
    }

    @Test
    void givenInvalidArticleIds_whenGetRestockDateIsCalled_thenReturnsEmptyList() {

        List<Integer> articleIds = List.of(1, 2, 3);

        when(supplyRepository.findLatestRestockDateForArticle("1")).thenReturn(null);
        when(supplyRepository.findLatestRestockDateForArticle("2")).thenReturn(null);
        when(supplyRepository.findLatestRestockDateForArticle("3")).thenReturn(null);

        List<LocalDate> restockDates = supplyJpaAdapter.getRestockDate(articleIds);

        assertTrue(restockDates.isEmpty());
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("1");
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("2");
        verify(supplyRepository, times(1)).findLatestRestockDateForArticle("3");
    }

    @Test
    void givenEmptyArticleIdList_whenGetRestockDateIsCalled_thenReturnsEmptyList() {

        List<Integer> articleIds = Collections.emptyList();

        List<LocalDate> restockDates = supplyJpaAdapter.getRestockDate(articleIds);

        assertTrue(restockDates.isEmpty());
        verify(supplyRepository, never()).findLatestRestockDateForArticle(anyString());
    }

    @Test
    void givenEmptyArticleIdList_whenGetRestockDateIsCalled_thenNoRepositoryInteraction() {

        List<Integer> articleIds = Collections.emptyList();

        supplyJpaAdapter.getRestockDate(articleIds);

        verifyNoInteractions(supplyRepository);
    }

}
