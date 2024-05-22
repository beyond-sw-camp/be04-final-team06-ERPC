package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.ContractFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractFileRepository extends JpaRepository<ContractFile, Long> {
}
