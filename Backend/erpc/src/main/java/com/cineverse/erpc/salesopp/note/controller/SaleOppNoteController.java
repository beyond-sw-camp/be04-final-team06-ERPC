package com.cineverse.erpc.salesopp.note.controller;

import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
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
    public ResponseEntity<SalesOppNoteDTO> registSalesOppNote(@RequestBody SalesOppNoteDTO oppNoteDTO) {
        salesOppNoteService.registSalesOppNote(oppNoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 영업기회 참고사항 수정 */
    @PatchMapping("/modify/{salesOppNoteId}")
    public ResponseEntity<SalesOppNote> modifySalesOppNote(@RequestBody SalesOppNoteDTO oppNote,
                                                           @PathVariable long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.modifySalesOppNote(salesOppNoteId, oppNote));
    }

    /* 영업기회 참고사항 삭제 */
    @PatchMapping("/delete/{salesOppNoteId}")
    public ResponseEntity<SalesOppNote> deleteSalesOppNote (@PathVariable long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.deleteSalesOppNote(salesOppNoteId));
    }

    /* 영업기회 참고사항 전체 조회 */
    @GetMapping("")
    public List<SalesOppNote> findSalesOppNoteList() {
        List<SalesOppNote> salesOppNoteList = salesOppNoteService.findsalesOppNoteList();

        return salesOppNoteList;
    }
}
