package com.cineverse.erpc.admin.delete.controller;

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
import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    private final DeleteService deleteService;

    @Autowired
    public DeleteController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @GetMapping("/sales_opp")
    @Operation(summary = "영업기회 삭제요청 전체조회", description = "영업기회 삭제요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<SalesOppDeleteRequest> findSalesOppDeleteRequest() {
        List<SalesOppDeleteRequest> salesOppDeleteRequestList = deleteService.findSalesOppDeleteRequestList();

        return salesOppDeleteRequestList;
    }

    @GetMapping("/sales_opp/{salesOppDeleteRequestId}")
    @Operation(summary = "영업기회 삭제요청 단일조회", description = "영업기회 삭제요청을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "삭제 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SalesOppDeleteRequestDTO findOppDeleteRequest(
            @Parameter(description = "영업기회 삭제요청 고유번호", required = true) @PathVariable long salesOppDeleteRequestId) {
        return deleteService.findSalesOppDeleteRequestById(salesOppDeleteRequestId);
    }

    @PatchMapping("/sales_opp/process")
    @Operation(summary = "영업기회 삭제요청 처리", description = "영업기회 삭제요청을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "처리 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppDeleteRequest> deleteSalesOpp(
            @Parameter(description = "삭제 요청 데이터", required = true) @RequestBody SalesOppDeleteRequest deleteOppRequest) {
        SalesOppDeleteRequest updatedOppRequest = deleteService.oppDeleteRequestProcess(deleteOppRequest);
        return ResponseEntity.ok(updatedOppRequest);
    }

    @GetMapping("/contract")
    @Operation(summary = "계약서 삭제요청 전체조회", description = "계약서 삭제요청을 전부 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<ContractDeleteRequest> findContractDeleteRequest() {
        return deleteService.findContractDeleteRequestList();
    }

    @GetMapping("/contract/{contractDeleteRequestId}")
    @Operation(summary = "계약서 삭제요청 단일조회", description = "계약서 삭제요청을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "삭제 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ContractDeleteRequestDTO findContractDeleteRequest(
            @Parameter(description = "계약서 삭제요청 고유번호", required = true) @PathVariable long contractDeleteRequestId) {
        return deleteService.findContractDeleteRequestById(contractDeleteRequestId);
    }

    @PatchMapping("/contract/process")
    @Operation(summary = "계약서 삭제요청 처리", description = "계약서 삭제요청을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "처리 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ContractDeleteRequest> deleteContract(
            @Parameter(description = "삭제 요청 데이터", required = true) @RequestBody ContractDeleteRequest deleteContract) {
        ContractDeleteRequest updatedContractRequest = deleteService.contractDeleteRequestProcess(deleteContract);
        return ResponseEntity.ok(updatedContractRequest);
    }

    @GetMapping("/quotation")
    @Operation(summary = "견적서 삭제요청 전체조회", description = "견적서 삭제요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseQuotationDeleteRequestList> findQuotationDeleteRequestList() {
        return deleteService.findQuotationDeleteRequestList();
    }

    @GetMapping("/quotation/{quotationDeleteRequestId}")
    @Operation(summary = "견적서 삭제요청 단일조회", description = "견적서 삭제요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseFindQuotationDeleteRequest> findQuotationDeleteRequestById(
            @Parameter(required = true, description = "견적서 삭제요청 고유번호")
            @PathVariable long quotationDeleteRequestId) {
        ResponseFindQuotationDeleteRequest responseQuotationDeleteRequest =
                deleteService.findQuotationDeleteRequestById(quotationDeleteRequestId);

        return ResponseEntity.status(HttpStatus.OK).body(responseQuotationDeleteRequest);
    }

    @PatchMapping("/quotation/permission")
    @Operation(summary = "영업기회 삭제요청 처리", description = "영업기회 삭제요청을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력 값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseQuotationDeleteRequestProcess> processQuotationDeleteRequest(
            @Parameter(required = true, description = "견적서 삭제처리 요청")
            @RequestBody RequestQuotationDeleteRequestProcess requestQuotationDeleteRequestProcess) {
        ResponseQuotationDeleteRequestProcess responseQuotationDeleteRequestProcess
                = deleteService.processQuotationDeleteRequest(requestQuotationDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(responseQuotationDeleteRequestProcess);
    }

    @GetMapping("/account")
    @Operation(summary = "거래처 삭제요청 전체조회", description = "거래처 삭제요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseAccountDeleteRequestList> findAccountDeleteRequestList() {
        return deleteService.findAccountDeleteList();
    }

    @GetMapping("/account/{accountDeleteRequestId}")
    @Operation(summary = "거래처 삭제요청 단일조회", description = "거래처 삭제요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseFindAccoundDeleteRequest findAccoundDeleteRequestById(
            @Parameter(required = true, description = "거래처 삭제요청 고유번호")
            @PathVariable long accountDeleteRequestId) {
        return deleteService.findAccountDeleteRequestById(accountDeleteRequestId);
    }

    @PatchMapping("/account/process")
    @Operation(summary = "거래처 삭제요청 처리", description = "거래처 삭제요청을 처리합니다..")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseAccountDeleteRequestProcess> accountDeleteRequestProcess(
            @Parameter(required = true, description = "거래처 삭제처리 요청")
            @RequestBody RequestAccountDeleteRequestProcess requestAccountDeleteRequestProcess) {
        ResponseAccountDeleteRequestProcess responseAccountDeleteRequestProcess
                = deleteService.accountDeleteRequestProcess(requestAccountDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(responseAccountDeleteRequestProcess);
    }

    @GetMapping("/order")
    @Operation(summary = "수주 삭제요청 전체조회", description = "수주 삭제요청을 전부 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseOrderDeleteRequestList> orderDeleteRequestList() {
        return deleteService.findOrderDeleteRequestList();
    }

    @GetMapping("/order/{orderDeleteRequestId}")
    @Operation(summary = "수주 삭제요청 단일조회", description = "수주 삭제요청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    private ResponseFindOrderDeleteRequest findOrderDeleteRequestById(
            @Parameter(required = true, description = "거래처 삭제요청 고유번호")
            @PathVariable long orderDeleteRequestId) {
        return deleteService.findOrderDeleteRequestById(orderDeleteRequestId);
    }

    @PatchMapping("/order/process")
    @Operation(summary = "수주 삭제요청 처리", description = "수주 삭제요청을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    private ResponseEntity<ResponseOrderDeleteRequestProcess> processOrderDeleteRequest(
            @Parameter(required = true, description = "수주 삭제처리 요청")
            @RequestBody RequestOrderDeleteRequestProcess requestOrderDeleteRequestProcess) {
        ResponseOrderDeleteRequestProcess orderDeleteRequestProcess =
                deleteService.processOrderDeleteRequest(requestOrderDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(orderDeleteRequestProcess);
    }
}
