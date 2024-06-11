package com.cineverse.erpc.slip.taxinvoiceissue.controller;

import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import com.cineverse.erpc.slip.taxinvoiceissue.dto.TaxInvoiceIssueDTO;
import com.cineverse.erpc.slip.taxinvoiceissue.service.TaxInvoiceIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "세금계산서 발행 처리", description = "세금계산서 요청 건을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    private ResponseEntity<TaxInvoiceIssueDTO> modifyTaxInvoiceIssue(
            @Parameter(description = "처리할 세금계산서 정보", required = true) @RequestBody TaxInvoiceIssue taxInvoiceIssue) {
        TaxInvoiceIssueDTO issueDTO = taxInvoiceIssueService.modifyIssue(taxInvoiceIssue);
        return ResponseEntity.status(HttpStatus.OK).body(issueDTO);
    }
}
