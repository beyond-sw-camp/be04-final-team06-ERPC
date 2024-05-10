package com.cineverse.erpc.salesopp.opportunity.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;

import java.util.List;

public interface SalesOppService {

    SalesOpp registSalesOpp(SalesOppDTO newOpp);

    SalesOpp modifySalesOpp(Long salesOppId, SalesOppDTO opp);

    SalesOpp changeSalesOppStatus(Long salesOppId, SalesOppDTO opp);

    List<SalesOpp> findSalesOppList();

    SalesOppDTO findSalesOppById(Long salesOppId);
}
