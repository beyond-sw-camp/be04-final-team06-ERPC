package com.cineverse.erpc.salesopp.opportunity.controller;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
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
    public ResponseEntity<SalesOppDTO> registSalesOpp(@RequestBody SalesOppDTO newOpp) {
        salesOppService.registSalesOpp(newOpp);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOpp);
    }

    /* 영업기회 수정 */
    @PatchMapping("/modify/{salesOppId}")
    public ResponseEntity<SalesOpp> modifySalesOpp(@RequestBody SalesOppDTO opp, @PathVariable long salesOppId) {
        return ResponseEntity.ok(salesOppService.modifySalesOpp(salesOppId, opp));
    }

    /* 영업기회 상태 변경 */
    @PatchMapping("/status/{salesOppId}")
    public ResponseEntity<SalesOpp> changeSalesOppStatus(@RequestBody SalesOppDTO opp, @PathVariable long salesOppId) {
        return ResponseEntity.ok(salesOppService.changeSalesOppStatus(salesOppId, opp));
    }

    /* 영업기회 삭제 요청 */
    @PostMapping("/delete")
    public ResponseEntity<SalesOppDeleteRequestDTO> deleteSalesOpp(@RequestBody SalesOppDeleteRequestDTO deleteOpp) {
        salesOppService.requestDeleteOpp(deleteOpp);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteOpp);
    }

    /* 영업기회 전체 조회 */
    @GetMapping("")
    public List<SalesOpp> findSalesOppList() {
        List<SalesOpp> salesOppList = salesOppService.findSalesOppList();

        return salesOppList;
    }

    /* 영업기회 단일 조회 */
    @GetMapping("/{salesOppId}")
    public SalesOppDTO findSalesOppById(@PathVariable long salesOppId) {
        SalesOppDTO salesOpp = salesOppService.findSalesOppById(salesOppId);

        return salesOpp;
    }
}
