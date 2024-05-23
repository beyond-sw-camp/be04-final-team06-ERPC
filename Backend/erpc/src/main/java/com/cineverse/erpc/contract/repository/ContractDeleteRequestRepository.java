package com.cineverse.erpc.contract.repository;

import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractDeleteRequestRepository extends JpaRepository<ContractDeleteRequest, Long> {
}
