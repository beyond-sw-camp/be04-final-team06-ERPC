package com.cineverse.erpc.salesopp.note.controller;

import com.cineverse.erpc.salesopp.note.aggregate.entity.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.note.service.SalesOppNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales_opp_note")
public class SaleOppNoteController {

    private final SalesOppNoteService salesOppNoteService;

    @Autowired
    public SaleOppNoteController(SalesOppNoteService salesOppNoteService) {
        this.salesOppNoteService = salesOppNoteService;
    }

    /* 영업기회 참고사항 작성 */
    @PostMapping("/regist")
    public ResponseEntity<SalesOppNoteDTO> registSalesOppNote(@RequestBody SalesOppNoteDTO newSalesOppNote) {
        salesOppNoteService.registSalesOppNote(newSalesOppNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSalesOppNote);
    }

    /* 영업기회 참고사항 수정 */
    @PatchMapping("/modify/{salesOppNoteId}")
    public ResponseEntity<SalesOppNote> modifySalesOppNote(@RequestBody SalesOppNoteDTO salesOppNote, @PathVariable Long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.modifySalesOppNote(salesOppNoteId, salesOppNote));
    }

    /* 영업기회 참고사항 삭제 */
    @PatchMapping("/delete/{salesOppNoteId}")
    public ResponseEntity<SalesOppNote> deleteSalesOppNote (@PathVariable Long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.deleteSalesOppNote(salesOppNoteId));
    }

    /* 영업기회 참고사항 전체 조회 */
    @GetMapping("/list")
    public List<SalesOppNote> findSalesOppNoteList() {
        List<SalesOppNote> salesOppNoteList = salesOppNoteService.findsalesOppNoteList();

        return salesOppNoteList;
    }
}