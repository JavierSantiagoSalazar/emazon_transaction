package com.pragma.emazon_transaction.application.mappers.supply;

import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SupplyRequestMapper {

    default Supply toDomain(List<SupplyRequest> supplyRequests) {
        return new Supply(
                null,
                LocalDate.now(),
                mapSupplyArticlesIds(supplyRequests),
                mapSupplyArticlesAmounts(supplyRequests)
        );
    }

    default List<Integer> mapSupplyArticlesIds(List<SupplyRequest> supplyRequests) {
        return supplyRequests.stream()
                .map(SupplyRequest::getArticleId)
                .toList();
    }

    default List<Integer> mapSupplyArticlesAmounts(List<SupplyRequest> supplyRequests) {
        return supplyRequests.stream()
                .map(SupplyRequest::getAmount)
                .toList();
    }

}
