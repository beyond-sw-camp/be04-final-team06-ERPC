package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.QuotationFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationFileRepository extends JpaRepository<QuotationFile, Long> {
}
