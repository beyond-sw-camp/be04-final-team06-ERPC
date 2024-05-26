package com.cineverse.erpc.slip.taxinvoiceissue.controller;

import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import com.cineverse.erpc.slip.taxinvoiceissue.dto.TaxInvoiceIssueDTO;
import com.cineverse.erpc.slip.taxinvoiceissue.service.TaxInvoiceIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issue")
public class TaxInvoiceIssueController {

    private final TaxInvoiceIssueService taxInvoiceIssueService;

    @Autowired
    public TaxInvoiceIssueController(TaxInvoiceIssueService taxInvoiceIssueService) {
        this.taxInvoiceIssueService = taxInvoiceIssueService;
    }

    @PatchMapping("/modify")
    private ResponseEntity<TaxInvoiceIssueDTO> modifyTaxInvoiceIssue(@RequestBody TaxInvoiceIssue taxInvoiceIssue) {

        TaxInvoiceIssueDTO issueDTO = taxInvoiceIssueService.modifyIssue(taxInvoiceIssue);

        return ResponseEntity.status(HttpStatus.OK).body(issueDTO);
    }
}
