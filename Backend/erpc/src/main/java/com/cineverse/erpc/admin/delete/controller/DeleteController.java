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
import com.cineverse.erpc.order.order.dto.ResponseDeleteOrder;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    /* 영업기회 삭제 요청 전체조회 */
    @GetMapping("/sales_opp")
    public List<SalesOppDeleteRequest> findSalesOppDeleteRequest() {
        List<SalesOppDeleteRequest> salesOppDeleteRequestList = deleteService.findSalesOppDeleteRequestList();

        return salesOppDeleteRequestList;
    }

    /* 영업기회 삭제 요청 단일조회 */
    @GetMapping("/sales_opp/{salesOppDeleteRequestId}")
    public SalesOppDeleteRequestDTO findOppDeleteRequest(@PathVariable long salesOppDeleteRequestId) {
        SalesOppDeleteRequestDTO oppDeleteRequest = deleteService.findSalesOppDeleteRequestById(salesOppDeleteRequestId);

        return oppDeleteRequest;
    }

    /* 영업기회 삭제 요청처리 */
    @PatchMapping("/sales_opp/status/{salesOppDeleteRequestId}")
    public ResponseEntity<SalesOppDeleteRequest> deleteSalesOpp(@RequestBody SalesOppDeleteRequestDTO deleteOppDTO,
                                                                @PathVariable long salesOppDeleteRequestId) {
        SalesOppDeleteRequest updatedOppRequest =
                deleteService.changeOppDeleteRequestStatus(salesOppDeleteRequestId, deleteOppDTO);
        return ResponseEntity.ok(updatedOppRequest);
    }

    /* 계약서 삭제 요청 전체조회 */
    @GetMapping("/contract")
    public List<ContractDeleteRequest> findContractDeleteRequest() {
        List<ContractDeleteRequest> contractDeleteRequestList = deleteService.findContractDeleteRequestList();

        return contractDeleteRequestList;
    }

    /* 계약서 삭제 요청 단일조회 */
    @GetMapping("/contract/{contractDeleteRequestId}")
    public ContractDeleteRequestDTO findContractDeleteRequest(@PathVariable long contractDeleteRequestId) {
        ContractDeleteRequestDTO contractDeleteRequest =
                deleteService.findContractDeleteRequestById(contractDeleteRequestId);

        return contractDeleteRequest;
    }

    /* 계약서 삭제 요청 처리 */
    @PatchMapping("/contract/status/{contractDeleteRequestId}")
    public ResponseEntity<ContractDeleteRequest> deleteContract(@RequestBody ContractDeleteRequestDTO deleteContractDTO,
                                                                @PathVariable long contractDeleteRequestId) {
        ContractDeleteRequest updatedContractRequest =
                deleteService.changeContractDeleteRequestStatus(deleteContractDTO, contractDeleteRequestId);

        return ResponseEntity.ok(updatedContractRequest);
    }

    @GetMapping("/quotation")
    public List<ResponseQuotationDeleteRequestList> findQuotationDeleteRequestList() {
        return deleteService.findQuotationDeleteRequestList();
    }

    @GetMapping("/quotation/{quotationDeleteRequestId}")
    public ResponseEntity<ResponseFindQuotationDeleteRequest> findQuotationDeleteRequestById(
            @PathVariable long quotationDeleteRequestId) {
        ResponseFindQuotationDeleteRequest responseQuotationDeleteRequest =
                deleteService.findQuotationDeleteRequestById(quotationDeleteRequestId);

        return ResponseEntity.status(HttpStatus.OK).body(responseQuotationDeleteRequest);
    }

    @PatchMapping("/quotation/process")
    public ResponseEntity<ResponseQuotationDeleteRequestProcess> processQuotationDeleteRequest(
            @RequestBody RequestQuotationDeleteRequestProcess requestQuotationDeleteRequestProcess) {
        ResponseQuotationDeleteRequestProcess responseQuotationDeleteRequestProcess
                = deleteService.processQuotationDeleteRequest(requestQuotationDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(responseQuotationDeleteRequestProcess);
    }

    @GetMapping("/account")
    public List<ResponseAccountDeleteRequestList> findAccountDeleteRequestList() {
        return deleteService.findAccountDeleteList();
    }

    @GetMapping("/account/{accountDeleteRequestId}")
    public ResponseFindAccoundDeleteRequest findAccoundDeleteRequestById(
            @PathVariable long accountDeleteRequestId) {
        return deleteService.findAccountDeleteRequestById(accountDeleteRequestId);
    }

    @PatchMapping("/account/process")
    public ResponseEntity<ResponseAccountDeleteRequestProcess> accountDeleteRequestProcess(
            @RequestBody RequestAccountDeleteRequestProcess requestAccountDeleteRequestProcess) {
        ResponseAccountDeleteRequestProcess responseAccountDeleteRequestProcess
                = deleteService.accountDeleteRequestProcess(requestAccountDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(responseAccountDeleteRequestProcess);
    }

    @GetMapping("/order")
    public List<ResponseOrderDeleteRequestList> orderDeleteRequestList() {
        return deleteService.findOrderDeleteRequestList();
    }

    @GetMapping("/order/{orderDeleteRequestId}")
    private ResponseFindOrderDeleteRequest findOrderDeleteRequestById(@PathVariable long orderDeleteRequestId) {
        return deleteService.findOrderDeleteRequestById(orderDeleteRequestId);
    }

    @PatchMapping("/order/process")
    private ResponseEntity<ResponseOrderDeleteRequestProcess> processOrderDeleteRequest(
            @RequestBody RequestOrderDeleteRequestProcess requestOrderDeleteRequestProcess) {
        ResponseOrderDeleteRequestProcess orderDeleteRequestProcess =
                deleteService.processOrderDeleteRequest(requestOrderDeleteRequestProcess);

        return ResponseEntity.status(HttpStatus.OK).body(orderDeleteRequestProcess);
    }
}
