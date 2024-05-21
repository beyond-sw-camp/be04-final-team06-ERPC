package com.cineverse.erpc.quotation.quotation.repo;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    Quotation findByQuotationCode(String quotationCode);
}
