package com.pragma.emazon_transaction.infrastructure.out.jpa.repository;

import com.pragma.emazon_transaction.infrastructure.out.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SupplyRepository extends JpaRepository<SupplyEntity, Integer> {

    @Query(value = "SELECT s.supply_restock_date FROM supply s WHERE FIND_IN_SET(:articleId, REPLACE(REPLACE(s.supply_article_ids, '[', ''), ']', '')) > 0 ORDER BY s.supply_date DESC LIMIT 1", nativeQuery = true)
    LocalDate findLatestRestockDateForArticle(@Param("articleId") String articleId);

}

