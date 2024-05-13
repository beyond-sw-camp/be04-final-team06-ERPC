package com.cineverse.erpc.contract.repository;

import com.cineverse.erpc.contract.aggregate.ContractProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractProductRepository extends JpaRepository<ContractProduct, Long> {
}
