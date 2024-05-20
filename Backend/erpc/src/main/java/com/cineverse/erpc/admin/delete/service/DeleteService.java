package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;

import java.util.List;

public interface DeleteService {

    List<SalesOppDeleteRequest> findSalesOppDeleteRequestList();

    SalesOppDeleteRequestDTO findSalesOppDeleteRequestById(int salesOppDeleteRequestId);

    SalesOppDeleteRequest changeRequestStatus(int salesOppDeleteRequestId, SalesOppDeleteRequestDTO deleteOpp);
}
