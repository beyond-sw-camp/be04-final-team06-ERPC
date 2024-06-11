package com.cineverse.erpc.salesopp.opportunity.repository;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOppStatusRepository extends JpaRepository<SalesOppStatus, Integer> {
}
