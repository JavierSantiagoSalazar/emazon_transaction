package com.pragma.emazon_transaction.infrastructure.out.jpa.repository;

import com.pragma.emazon_transaction.infrastructure.out.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Integer> {
}
