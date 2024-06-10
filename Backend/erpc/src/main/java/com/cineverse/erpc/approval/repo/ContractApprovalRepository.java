package com.cineverse.erpc.approval.repo;

import com.cineverse.erpc.approval.aggregate.ContractApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractApprovalRepository extends JpaRepository<ContractApproval, Long> {
}
