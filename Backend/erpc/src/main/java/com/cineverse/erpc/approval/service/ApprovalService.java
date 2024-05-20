package com.cineverse.erpc.approval.service;

import com.cineverse.erpc.approval.dto.contract.RequestRegistContractApproval;
import com.cineverse.erpc.approval.dto.contract.ResponseContractApprovalList;
import com.cineverse.erpc.approval.dto.contract.ResponseFindContractApproval;
import com.cineverse.erpc.approval.dto.contract.ResponseRegistContractApproval;
import com.cineverse.erpc.approval.dto.quotation.RequestRegistQuotationApproval;
import com.cineverse.erpc.approval.dto.quotation.ResponseFindQuotationApproval;
import com.cineverse.erpc.approval.dto.quotation.ResponseQuotationApprovalList;
import com.cineverse.erpc.approval.dto.quotation.ResponseRegistQuotationApproval;
import com.cineverse.erpc.approval.dto.shipment.RequestRegistShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseFindShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseRegistShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseShipmentApprovalList;

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
}
