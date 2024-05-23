package com.cineverse.erpc.slip.taxinvoice.controller;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceProcess;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceProcessDTO;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.service.TaxInvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/tax_invoice")
public class TaxInvoiceController {

    private final TaxInvoiceService taxInvoiceService;

    @Autowired
    public TaxInvoiceController(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }

    /* 세금계산서 요청  */
    @PostMapping("/regist")
    public ResponseEntity<TaxInvoiceRequestDTO> registTaxInvoiceRequest(@RequestPart("taxInvoiceRequest") String taxInvoiceJson,
                                                                        @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        String utf8Json = new String(taxInvoiceJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();

        TaxInvoiceRequestDTO newTaxInvoice = objectMapper.readValue(utf8Json, TaxInvoiceRequestDTO.class);

        taxInvoiceService.registTaxInvoiceRequest(newTaxInvoice, files);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTaxInvoice);
    }

    /* 요청 세금계산서 전체 조회   */
    @GetMapping("")
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        List<TaxInvoiceRequest> taxInvoiceRequestList = taxInvoiceService.findTaxInvoiceList();

        return taxInvoiceRequestList;
    }

    /* 요청 세금계산서 단일 조회  */
    @GetMapping("/{taxInvoiceRequestId}")
    public TaxInvoiceRequestDTO findTaxInvoiceRequestById(@PathVariable long taxInvoiceRequestId) {
        TaxInvoiceRequestDTO taxInvoice = taxInvoiceService.findTaxInvoiceById(taxInvoiceRequestId);

        return taxInvoice;
    }

    /* 세금계산서 처리 */
    @PatchMapping("/process/{taxInvoiceProcessId}")
    public ResponseEntity<TaxInvoiceProcess> modifyProcessStatus(@RequestBody TaxInvoiceProcessDTO processDTO,
                                                                 @PathVariable long taxInvoiceProcessId) {
        TaxInvoiceProcess updatedProcess = taxInvoiceService.modifyProcess(taxInvoiceProcessId, processDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    /* 세금계산서 처리 전체 조회 */
    @GetMapping("/process")
    public List<TaxInvoiceProcess> findTaxInvoiceProcessList() {
        List<TaxInvoiceProcess> processList = taxInvoiceService.findProcessList();

        return processList;
    }
}
