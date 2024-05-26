package com.cineverse.erpc.slip.taxinvoiceissue.repository;

import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxInvoiceIssueRepository extends JpaRepository<TaxInvoiceIssue, Long> {
}
