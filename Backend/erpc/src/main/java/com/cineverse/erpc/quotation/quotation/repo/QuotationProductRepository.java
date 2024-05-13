package com.cineverse.erpc.quotation.quotation.repo;

import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationProductRepository extends JpaRepository<QuotationProduct, Integer> {
}
