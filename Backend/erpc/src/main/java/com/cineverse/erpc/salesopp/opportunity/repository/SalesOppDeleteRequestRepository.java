package com.cineverse.erpc.salesopp.opportunity.repository;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOppDeleteRequestRepository extends JpaRepository<SalesOppDeleteRequest, Long> {
}
