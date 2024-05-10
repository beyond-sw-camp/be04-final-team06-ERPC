package com.cineverse.erpc.salesopp.opportunity.repository;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOppRepository extends JpaRepository<SalesOpp, Long> {
}
