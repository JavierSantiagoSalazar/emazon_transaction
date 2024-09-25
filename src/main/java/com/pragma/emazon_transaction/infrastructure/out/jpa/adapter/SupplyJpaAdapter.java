package com.pragma.emazon_transaction.infrastructure.out.jpa.adapter;

import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.spi.SupplyPersistencePort;
import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.SupplyEntityMapper;
import com.pragma.emazon_transaction.infrastructure.out.jpa.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements SupplyPersistencePort {

    private final SupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    @Override
    public void saveSupplyTransaction(Supply supply) {
        supplyRepository.save(supplyEntityMapper.toEntity(supply));
    }

    @Override
    public List<LocalDate> getRestockDate(List<Integer> articleIdList) {
        List<LocalDate> restockDates = new ArrayList<>();

        for (Integer articleId : articleIdList) {
            LocalDate restockDate = supplyRepository.findLatestRestockDateForArticle(articleId.toString());
            if (restockDate != null) {
                restockDates.add(restockDate);
            }
        }

        return restockDates;
    }

}
