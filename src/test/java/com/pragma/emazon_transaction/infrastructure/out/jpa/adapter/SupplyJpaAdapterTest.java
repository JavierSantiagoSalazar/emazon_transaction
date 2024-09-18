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
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

        defaultSupply = new Supply(1, LocalDate.EPOCH, List.of(10, 5), List.of(100, 600));
        defaultSupplyEntity = new SupplyEntity(1, LocalDate.EPOCH, List.of(10, 5), List.of(100, 600));
    }

    @Test
    void givenSupply_whenSaveSupplyTransactionIsCalled_thenSupplyIsSaved() {

        when(supplyEntityMapper.toEntity(defaultSupply)).thenReturn(defaultSupplyEntity);

        supplyJpaAdapter.saveSupplyTransaction(defaultSupply);

        verify(supplyRepository, times(1)).save(defaultSupplyEntity);
        verify(supplyEntityMapper, times(1)).toEntity(defaultSupply);
    }

}
