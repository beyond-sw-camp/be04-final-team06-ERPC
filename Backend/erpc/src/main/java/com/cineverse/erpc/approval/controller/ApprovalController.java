package com.cineverse.erpc.approval.controller;

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
import com.cineverse.erpc.approval.service.ApprovalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
    private final ModelMapper mapper;
    private final ApprovalService approvalService;

    @Autowired
    public ApprovalController(ModelMapper mapper, ApprovalService approvalService) {
        this.mapper = mapper;
        this.approvalService = approvalService;
    }

    @PostMapping("/contract/regist")
    public ResponseEntity<ResponseRegistContractApproval> registContractApproval(
            @RequestBody RequestRegistContractApproval requestApproval) {

        ResponseRegistContractApproval responseApproval = approvalService.registContractApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }
    @PostMapping("/shipment/regist")
    public ResponseEntity<ResponseRegistShipmentApproval> registShipmentApproval(
            @RequestBody RequestRegistShipmentApproval requestApproval) {

        ResponseRegistShipmentApproval responseApproval = approvalService.registShipmentApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }
    @PostMapping("/quotation/regist")
    public ResponseEntity<ResponseRegistQuotationApproval> registQuotationApproval(
            @RequestBody RequestRegistQuotationApproval requestApproval) {

        ResponseRegistQuotationApproval responseApproval = approvalService.registQuotationtApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }

    @GetMapping("/contract")
    public List<ResponseContractApprovalList> findContractApprovalList() {
        return approvalService.findContractApprovalList();
    }
    @GetMapping("/quotation")
    public List<ResponseQuotationApprovalList> findQuotationApprovalList() {
        return approvalService.findQuotationApprovalList();
    }
    @GetMapping("/shipment")
    public List<ResponseShipmentApprovalList> findShipmentApprovalList() {
        return approvalService.findShipmentApprovalList();
    }

    @GetMapping("/contract/{contractId}")
    public ResponseFindContractApproval findContractApproval(@PathVariable long contractId) {
        return approvalService.findContractApproval(contractId);
    }
    @GetMapping("/quotation/{quotationId}")
    public ResponseFindQuotationApproval findQuotationApproval(@PathVariable long quotationId) {
        return approvalService.findQuotationApproval(quotationId);
    }

    @GetMapping("/shipment/{orderRegistrationId}")
    public ResponseFindShipmentApproval findShipmentApproval(@PathVariable long orderRegistrationId) {
        return approvalService.findShipmentApproval(orderRegistrationId);
    }
}
