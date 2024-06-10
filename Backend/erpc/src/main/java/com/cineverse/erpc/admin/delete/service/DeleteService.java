package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.admin.delete.dto.account.RequestAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseFindAccoundDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.RequestOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.order.ResponseFindOrderDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.RequestQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseFindQuotationDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestProcess;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.order.order.dto.ResponseDeleteOrder;
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

    List<ResponseQuotationDeleteRequestList> findQuotationDeleteRequestList();

    ResponseFindQuotationDeleteRequest findQuotationDeleteRequestById(long quotationDeleteRequestId);

    ResponseQuotationDeleteRequestProcess processQuotationDeleteRequest(RequestQuotationDeleteRequestProcess requestQuotationDeleteRequestProcess);

    List<ResponseAccountDeleteRequestList> findAccountDeleteList();

    ResponseFindAccoundDeleteRequest findAccountDeleteRequestById(long accountDeleteRequestId);

    ResponseAccountDeleteRequestProcess accountDeleteRequestProcess(RequestAccountDeleteRequestProcess requestAccountDeleteRequestProcess);

    List<ResponseOrderDeleteRequestList> findOrderDeleteRequestList();

    ResponseFindOrderDeleteRequest findOrderDeleteRequestById(long orderDeleteRequestId);

    ResponseOrderDeleteRequestProcess processOrderDeleteRequest(RequestOrderDeleteRequestProcess requestOrderDeleteRequestProcess);
}
