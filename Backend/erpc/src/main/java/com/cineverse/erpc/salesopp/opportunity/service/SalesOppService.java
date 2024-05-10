package com.cineverse.erpc.salesopp.opportunity.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;

import java.util.List;

public interface SalesOppService {
    SalesOpp registSalesOpp(SalesOppDTO newSalesOpp);

    SalesOppDTO modifySalesOpp(Long salesOppId, SalesOppDTO salesOpp);

    List<SalesOpp> findSalesOppList();

    SalesOppDTO findSalesOppById(Long salesOppId);

}
