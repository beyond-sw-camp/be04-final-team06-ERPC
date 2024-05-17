package com.cineverse.erpc.slip.taxinvoice.controller;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.service.TaxInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TaxInvoiceRequestDTO> registTaxInvoiceRequest(@RequestBody TaxInvoiceRequestDTO newTaxInvoice) {
        taxInvoiceService.registTaxInvoiceRequest(newTaxInvoice);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTaxInvoice);
    }

    /* 세금계산서 처리  */
//    @PatchMapping("/process")

    /* 요청 세금계산서 전체 조회   */
    @GetMapping("/list")
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        List<TaxInvoiceRequest> taxInvoiceRequestList = taxInvoiceService.findTaxInvoiceList();

        return taxInvoiceRequestList;
    }

    /* 요청 세금계산서 단일 조회  */
    @GetMapping("/{taxInvoiceRequestId}")
    public TaxInvoiceRequestDTO findTaxInvoiceRequestById(@PathVariable Long taxInvoiceRequestId) {
        TaxInvoiceRequestDTO taxInvoice = taxInvoiceService.findTaxInvoiceById(taxInvoiceRequestId);

        return taxInvoice;
    }
}
