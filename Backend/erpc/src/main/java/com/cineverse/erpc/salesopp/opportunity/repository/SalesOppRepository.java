package com.cineverse.erpc.salesopp.opportunity.repository;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOppRepository extends JpaRepository<SalesOpp, Long> {
    List<SalesOpp> findByOppDeleteDateIsNullOrderBySalesOppIdDesc();
}
