package com.cineverse.erpc.contract.controller;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "계약서 작성", description = "새로운 계약서를 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ContractDTO> registContract(
            @Parameter(description = "계약서 JSON 데이터", required = true) @RequestPart("contract") String contractJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ContractDTO newContract = objectMapper.readValue(contractJson, ContractDTO.class);
        contractService.registContract(newContract, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContract);
    }

    @PatchMapping(path = "/modify/{contractId}", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "계약서 수정", description = "기존 계약서를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<Contract> modifyContract(
            @Parameter(description = "수정할 계약서 JSON 데이터", required = true) @RequestPart("contract") String contractJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files,
            @Parameter(description = "계약서 ID", required = true) @PathVariable Long contractId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ContractDTO contract = objectMapper.readValue(contractJson, ContractDTO.class);
        contractService.modifyContract(contractId, contract, files);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    @Operation(summary = "계약서 삭제 요청", description = "계약서 삭제 요청을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "삭제 요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ContractDeleteRequestDTO> deleteContract(
            @Parameter(description = "계약서 삭제 요청 데이터", required = true) @RequestBody ContractDeleteRequestDTO deleteContract) {
        contractService.requestDeleteContract(deleteContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteContract);
    }

    @GetMapping("")
    @Operation(summary = "계약서 전체 조회", description = "등록된 모든 계약서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Contract> findContractList() {
        return contractService.findContractList();
    }

    @GetMapping("/{contractId}")
    @Operation(summary = "계약서 단일 조회", description = "특정 계약서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "계약서를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ContractDTO findContractById(
            @Parameter(description = "계약서 ID", required = true) @PathVariable Long contractId) {
        return contractService.findContractById(contractId);
    }

    @GetMapping("/products")
    @Operation(summary = "계약서 상품 전체 조회", description = "모든 계약서 상품을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<ContractProduct> findContractProductList() {
        return contractService.findContractProductList();
    }

    @GetMapping("/code")
    @Operation(summary = "계약서 코드로 계약서 조회", description = "특정 계약서 코드를 통해 계약서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "계약서를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ContractDTO findContractByCode(
            @Parameter(description = "계약서 코드", required = true) @RequestParam String contractCode) {
        return contractService.findContractByContractCode(contractCode);
    }
}
