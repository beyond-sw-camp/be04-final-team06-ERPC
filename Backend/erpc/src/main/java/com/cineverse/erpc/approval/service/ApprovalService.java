package com.cineverse.erpc.approval.service;

import com.cineverse.erpc.approval.dto.contract.*;
import com.cineverse.erpc.approval.dto.quotation.*;
import com.cineverse.erpc.approval.dto.shipment.*;

import java.util.List;

public interface ApprovalService {
    ResponseRegistContractApproval registContractApproval(RequestRegistContractApproval requestApproval);
    ResponseRegistQuotationApproval registQuotationtApproval(RequestRegistQuotationApproval requestApproval);
    ResponseRegistShipmentApproval registShipmentApproval (RequestRegistShipmentApproval requestApproval);

    List<ResponseContractApprovalList> findContractApprovalList();

    List<ResponseQuotationApprovalList> findQuotationApprovalList();

    List<ResponseShipmentApprovalList> findShipmentApprovalList();

    ResponseFindContractApproval findContractApproval(long contractId);
    ResponseFindQuotationApproval findQuotationApproval(long quotationId);
    ResponseFindShipmentApproval findShipmentApproval(long contractId);

    ResponseContractApprovalProcess contractApprovalProcess(RequestContractApprovalProcess requestApproval);

    ResponseQuotationApprovalProcess quotationApprovalProcess(RequestQuotationApprovalProcess requestApproval);

    ResponseShipmentApprovalProcess shipmentApprovalProcess(RequestShipmentApprovalProcess requestApproval);
}
