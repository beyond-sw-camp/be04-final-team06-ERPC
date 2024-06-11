package com.cineverse.erpc.slip.taxinvoice.controller;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.service.TaxInvoiceService;
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
@RequestMapping("/tax_invoice")
public class TaxInvoiceController {

    private final TaxInvoiceService taxInvoiceService;

    @Autowired
    public TaxInvoiceController(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }

    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "세금계산서 요청", description = "새로운 세금계산서를 요청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<TaxInvoiceRequestDTO> registTaxInvoiceRequest(
            @Parameter(description = "세금계산서 요청 데이터", required = true) @RequestPart("taxInvoiceRequest") String taxInvoiceJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        TaxInvoiceRequestDTO newTaxInvoice = objectMapper.readValue(taxInvoiceJson, TaxInvoiceRequestDTO.class);
        taxInvoiceService.registTaxInvoiceRequest(newTaxInvoice, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTaxInvoice);
    }

    @GetMapping("")
    @Operation(summary = "요청 세금계산서 전체 조회", description = "모든 요청된 세금계산서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        return taxInvoiceService.findTaxInvoiceList();
    }

    @GetMapping("/{taxInvoiceRequestId}")
    @Operation(summary = "요청 세금계산서 단일 조회", description = "특정 세금계산서 요청을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "세금계산서 요청을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public TaxInvoiceRequestDTO findTaxInvoiceRequestById(
            @Parameter(description = "세금계산서 요청 ID", required = true) @PathVariable long taxInvoiceRequestId) {
        return taxInvoiceService.findTaxInvoiceById(taxInvoiceRequestId);
    }
}
