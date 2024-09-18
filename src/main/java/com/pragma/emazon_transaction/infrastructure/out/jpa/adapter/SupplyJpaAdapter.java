package com.pragma.emazon_transaction.infrastructure.out.jpa.adapter;

import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyEntityMapper;
import com.pragma.emazon_transaction.infrastructure.out.jpa.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements SupplyPersistencePort {

    private final SupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    @Override
    public void saveSupplyTransaction(Supply supply) {
        supplyRepository.save(supplyEntityMapper.toEntity(supply));
    }

}
