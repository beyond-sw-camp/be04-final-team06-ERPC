package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;

import java.util.List;

public interface DeleteService {

    List<SalesOppDeleteRequest> findSalesOppDeleteRequestList();

    SalesOppDeleteRequestDTO findSalesOppDeleteRequestById(long salesOppDeleteRequestId);

    SalesOppDeleteRequest changeOppDeleteRequestStatus(long salesOppDeleteRequestId,
                                                       SalesOppDeleteRequestDTO deleteOpp);

    List<ContractDeleteRequest> findContractDeleteRequestList();

    ContractDeleteRequestDTO findContractDeleteRequestById(long contractDeleteRequestId);

    ContractDeleteRequest changeContractDeleteRequestStatus(ContractDeleteRequestDTO deleteContractDTO,
                                                            long contractDeleteRequestId);
}
