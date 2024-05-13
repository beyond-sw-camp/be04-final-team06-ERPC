package com.cineverse.erpc.contract.repository;

import com.cineverse.erpc.contract.aggregate.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByContractDeleteDateIsNull();
}
