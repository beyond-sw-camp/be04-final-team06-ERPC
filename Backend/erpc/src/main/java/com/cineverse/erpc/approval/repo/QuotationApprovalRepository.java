package com.cineverse.erpc.approval.repo;

import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationApprovalRepository extends JpaRepository<QuotationApproval, Long> {
}
