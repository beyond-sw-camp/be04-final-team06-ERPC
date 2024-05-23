package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.ContractFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractFileRepository extends JpaRepository<ContractFile, Long> {

    List<ContractFile> findByContract_ContractId(long contractId);
}
