package com.cineverse.erpc.approval.controller;

import com.cineverse.erpc.approval.dto.contract.*;
import com.cineverse.erpc.approval.dto.quotation.*;
import com.cineverse.erpc.approval.dto.shipment.*;
import com.cineverse.erpc.approval.service.ApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "계약서 결재요청", description = "계약서 결재요청을 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistContractApproval> registContractApproval(
            @Parameter(required = true, description = "견적서 결재요청 ")
            @RequestBody RequestRegistContractApproval requestApproval) {

        ResponseRegistContractApproval responseApproval = approvalService.registContractApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }
    @PostMapping("/shipment/regist")
    @Operation(summary = "수주 결재요청", description = "수주 결재요청을 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistShipmentApproval> registShipmentApproval(
            @Parameter(required = true, description = "수주 결재요청")
            @RequestBody RequestRegistShipmentApproval requestApproval) {

        ResponseRegistShipmentApproval responseApproval = approvalService.registShipmentApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }
    @PostMapping("/quotation/regist")
    @Operation(summary = "견적서 결재요청", description = "견적서 결재요청을 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistQuotationApproval> registQuotationApproval(
            @Parameter(required = true, description = "견적서 결재요청")
            @RequestBody RequestRegistQuotationApproval requestApproval) {

        ResponseRegistQuotationApproval responseApproval = approvalService.registQuotationtApproval(requestApproval);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseApproval);
    }

    @GetMapping("/contract")
    @Operation(summary = "계약서 결재요청 전체조회", description = "계약서 결재요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseContractApprovalList> findContractApprovalList() {
        return approvalService.findContractApprovalList();
    }
    @GetMapping("/quotation")
    @Operation(summary = "견적서 결재요청 전체조회", description = "견적서 결재요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseQuotationApprovalList> findQuotationApprovalList() {
        return approvalService.findQuotationApprovalList();
    }
    @GetMapping("/shipment")
    @Operation(summary = "수주 결재요청 전체조회", description = "수주 결재요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseShipmentApprovalList> findShipmentApprovalList() {
        return approvalService.findShipmentApprovalList();
    }

    @GetMapping("/contract/{contractId}")
    @Operation(summary = "계약서 결재요청 단일조회", description = "계약서 결재요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseFindContractApproval findContractApproval(
            @Parameter(required = true, description = "계약서 결재요청 고유번호")
            @PathVariable long contractId) {
        return approvalService.findContractApproval(contractId);
    }
    @GetMapping("/quotation/{quotationId}")
    @Operation(summary = "견적서 결재요청 단일조회", description = "견적서 결재요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseFindQuotationApproval findQuotationApproval(
            @Parameter(required = true, description = "견적서 결재요청 고유번호")
            @PathVariable long quotationId) {
        return approvalService.findQuotationApproval(quotationId);
    }

    @GetMapping("/shipment/{orderRegistrationId}")
    @Operation(summary = "수주 결재요청 단일조회", description = "수주 결재요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseFindShipmentApproval findShipmentApproval(
            @Parameter(required = true, description = "수주 결재요청 고유번호")
            @PathVariable long orderRegistrationId) {
        return approvalService.findShipmentApproval(orderRegistrationId);
    }

    @PatchMapping("/contract/process")
    @Operation(summary = "계약서 결재요청 처리", description = "계약서 결재요청을 처리.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseContractApprovalProcess> contractApprovalProcess(
            @Parameter(required = true, description = "계약서 결재처리 요청")
            @RequestBody RequestContractApprovalProcess requestApproval) {
        ResponseContractApprovalProcess responseApproval = approvalService.contractApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
    @PatchMapping("/quotation/process")
    @Operation(summary = "견적서 결재요청 처리", description = "견적서 결재요청을 처리.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseQuotationApprovalProcess> quotationApprovalProcess(
            @Parameter(required = true, description = "견적서 결재처리 요청")
            @RequestBody RequestQuotationApprovalProcess requestApproval) {
        ResponseQuotationApprovalProcess responseApproval = approvalService.quotationApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
    @PatchMapping("/shipment/process")
    @Operation(summary = "수주 결재요청 처리", description = "수주 결재요청을 처리.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseShipmentApprovalProcess> shipmentApprovalProcess(
            @Parameter(required = true, description = "수주 결재처리 요청")
            @RequestBody RequestShipmentApprovalProcess requestApproval) {
        ResponseShipmentApprovalProcess responseApproval = approvalService.shipmentApprovalProcess(requestApproval);

        return ResponseEntity.status(HttpStatus.OK).body(responseApproval);
    }
}
