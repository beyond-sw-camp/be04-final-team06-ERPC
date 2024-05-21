package com.cineverse.erpc.contract.controller;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    /* 계약서 작성 */
    @PostMapping("/regist")
    public ResponseEntity<ContractDTO> registContract(@RequestBody ContractDTO newContract) {
        contractService.registContract(newContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContract);
    }

    /* 계약서 수정 */
    @PatchMapping("/modify/{contractId}")
    public ResponseEntity<Contract> modifyContract(@RequestBody ContractDTO contract, @PathVariable Long contractId) {
        return ResponseEntity.ok(contractService.modifyContract(contractId, contract));
    }

    /* 계약서 삭제 요청 */
    @PostMapping("/delete")
    public ResponseEntity<ContractDeleteRequestDTO> deleteContract(@RequestBody ContractDeleteRequestDTO deleteContract) {
        contractService.requestDeleteContract(deleteContract);

        return ResponseEntity.status(HttpStatus.CREATED).body(deleteContract);
    }

    /* 계약서 전체 조회 */
    @GetMapping("")
    public List<Contract> findContractList() {
        List<Contract> contractList = contractService.findContractList();

        return contractList;
    }

    /* 계약서 단일 조회  */
    @GetMapping("/{contractId}")
    public ContractDTO findContractById(@PathVariable Long contractId) {
        ContractDTO contract = contractService.findContractById(contractId);

        return contract;
    }

    /* 계약서 상품 전체 조회 */
    @GetMapping("/products")
    public List<ContractProduct> findContractProductList() {
        List<ContractProduct> productList = contractService.findContractProductList();

        return productList;
    }
}
