package com.cineverse.erpc.file.repository;

import com.cineverse.erpc.file.aggregate.QuotationFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationFileRepository extends JpaRepository<QuotationFile, Long> {

    List<QuotationFile> findByQuotation_QuotationId(long quotationId);
}
