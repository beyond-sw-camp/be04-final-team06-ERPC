package com.cineverse.erpc.admin.delete.controller;

import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PatchMapping("/contract/delete/{contractDeleteRequestId}")
    public ResponseEntity<ContractDeleteRequest> deleteContract(@RequestBody ContractDeleteRequestDTO deleteContractDTO,
                                                                @PathVariable long contractDeleteRequestId) {
        ContractDeleteRequest updatedContractRequest =
                deleteService.changeContractDeleteRequestStatus(deleteContractDTO, contractDeleteRequestId);

        return ResponseEntity.ok(updatedContractRequest);
    }
}
