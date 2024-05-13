package com.cineverse.erpc.quotation.quotation.repo;

import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationProductRepository extends JpaRepository<QuotationProduct, Integer> {

    List<QuotationProduct> findByQuotationQuotationId(long quotationId);
}
