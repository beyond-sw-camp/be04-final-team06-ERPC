package com.cineverse.erpc.quotation.quotation.repo;

import com.cineverse.erpc.quotation.quotation.aggregate.QuotationDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationDeleteRequestRepository extends JpaRepository<QuotationDeleteRequest, Long> {
}
