package com.cineverse.erpc.approval.controller;

import com.cineverse.erpc.approval.dto.contract.*;
import com.cineverse.erpc.approval.dto.quotation.*;
import com.cineverse.erpc.approval.dto.shipment.*;
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

    @PatchMapping("/contract/process")
    public ResponseEntity<ResponseContractApprovalProcess> contractApprovalProcess(
            @RequestBody RequestContractApprovalProcess requestApproval) {
        ResponseContractApprovalProcess responseApproval = approvalService.contractApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
    @PatchMapping("/quotation/process")
    public ResponseEntity<ResponseQuotationApprovalProcess> quotationApprovalProcess(
            @RequestBody RequestQuotationApprovalProcess requestApproval) {
        ResponseQuotationApprovalProcess responseApproval = approvalService.quotationApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
    @PatchMapping("/shipment/process")
    public ResponseEntity<ResponseShipmentApprovalProcess> shipmentApprovalProcess(
            @RequestBody RequestShipmentApprovalProcess requestApproval) {
        ResponseShipmentApprovalProcess responseApproval = approvalService.shipmentApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
}
