package com.cineverse.erpc.salesopp.opportunity.controller;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.service.SalesOppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales_opportunity")
public class SalesOppController {

    private final SalesOppService salesOppService;

    @Autowired

    public SalesOppController(SalesOppService salesOppService) {
        this.salesOppService = salesOppService;
    }

    /* 영업기회 등록 */
    @PostMapping("/regist")
    public ResponseEntity<SalesOppDTO> registSalesOpp(@RequestBody SalesOppDTO newSalesOpp) {
        salesOppService.registSalesOpp(newSalesOpp);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSalesOpp);
    }

    /* 영업기회 수정 */
    @PatchMapping("/modify/{salesOppId}")
    public ResponseEntity<SalesOppDTO> modifySalesOpp(@RequestBody SalesOppDTO salesOpp, @PathVariable Long salesOppId) {
        return ResponseEntity.ok(salesOppService.modifySalesOpp(salesOppId, salesOpp));
    }

    /* 영업기회 삭제 요청 */

    /* 영업기회 전체 조회 */
    @GetMapping("/list")
    public List<SalesOpp> findSalesOppList() {
        List<SalesOpp> salesOppList = salesOppService.findSalesOppList();

        return salesOppList;
    }

    /* 영업기회 단일 조회 */
    @GetMapping("/{salesOppId}")
    public SalesOppDTO findSalesOppById(@PathVariable Long salesOppId) {
        SalesOppDTO salesOpp = salesOppService.findSalesOppById(salesOppId);

        return salesOpp;
    }

}
