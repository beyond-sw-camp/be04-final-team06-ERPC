package com.cineverse.erpc.contract.controller;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
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
    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    public ResponseEntity<ContractDTO> registContract(@RequestPart("contract") String contractJson,
                                                      @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ContractDTO newContract = objectMapper.readValue(contractJson, ContractDTO.class);

        contractService.registContract(newContract, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(newContract);
    }

    /* 계약서 수정 */
    @PatchMapping(path = "/modify/{contractId}", consumes = {"multipart/form-data;charset=UTF-8"})
    public ResponseEntity<Contract> modifyContract(@RequestPart("contract") String contractJson,
                                                   @RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @PathVariable Long contractId) throws JsonProcessingException {

       ObjectMapper objectMapper = new ObjectMapper();
       ContractDTO contract = objectMapper.readValue(contractJson, ContractDTO.class);

       contractService.modifyContract(contractId, contract, files);

       return ResponseEntity.ok().build();
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

    /* 계약서 코드로 계약서 조회 */
    @GetMapping("/code")
    public ContractDTO findContractByCode(@RequestParam String contractCode) {
        return contractService.findContractByContractCode(contractCode);
    }
}
