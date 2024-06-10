package com.cineverse.erpc.approval.repo;

import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentApprovalRepository extends JpaRepository<ShipmentApproval, Long> {
}
