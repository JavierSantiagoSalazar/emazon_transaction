package com.pragma.emazon_transaction.infrastructure.out.jpa.entity;

import com.pragma.emazon_transaction.infrastructure.out.jpa.mapper.IntegerListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_id")
    private Integer supplyId;

    @Column(name = "supply_date", nullable = false)
    private LocalDate supplyDate;

    @Column(name = "supply_article_ids", nullable = false)
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> supplyArticleIds;

    @Column(name = "supply_amount", nullable = false)
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> supplyArticleAmounts;

}
