package com.cineverse.erpc.contract.repository;

import com.cineverse.erpc.contract.aggregate.ContractProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractProductRepository extends JpaRepository<ContractProduct, Long> {

    List<ContractProduct> findByContractContractId(long contractId);

    void deleteByContractContractId(long contractId);
}
