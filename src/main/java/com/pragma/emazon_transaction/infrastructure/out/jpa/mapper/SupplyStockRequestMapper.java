package com.pragma.emazon_transaction.infrastructure.out.jpa.mapper;

import com.pragma.emazon_transaction.application.dto.supply.SupplyStockRequest;
import com.pragma.emazon_transaction.domain.model.Supply;
import com.pragma.emazon_transaction.domain.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SupplyStockRequestMapper {

    SupplyStockRequest toRequest(Supply supply);

}


