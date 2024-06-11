package com.cineverse.erpc.salesopp.note.controller;

import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.note.service.SalesOppNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/regist")
    @Operation(summary = "영업기회 참고사항 작성", description = "새로운 영업기회 참고사항을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppNoteDTO> registSalesOppNote(
            @Parameter(description = "영업기회 참고사항 데이터", required = true) @RequestBody SalesOppNoteDTO oppNoteDTO) {
        salesOppNoteService.registSalesOppNote(oppNoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/modify/{salesOppNoteId}")
    @Operation(summary = "영업기회 참고사항 수정", description = "기존 영업기회 참고사항을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppNote> modifySalesOppNote(
            @Parameter(description = "수정할 영업기회 참고사항 데이터", required = true) @RequestBody SalesOppNoteDTO oppNote,
            @Parameter(description = "영업기회 참고사항 ID", required = true) @PathVariable long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.modifySalesOppNote(salesOppNoteId, oppNote));
    }

    @PatchMapping("/delete/{salesOppNoteId}")
    @Operation(summary = "영업기회 참고사항 삭제", description = "기존 영업기회 참고사항을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppNote> deleteSalesOppNote(
            @Parameter(description = "영업기회 참고사항 ID", required = true) @PathVariable long salesOppNoteId) {
        return ResponseEntity.ok(salesOppNoteService.deleteSalesOppNote(salesOppNoteId));
    }

    @GetMapping("")
    @Operation(summary = "영업기회 참고사항 전체 조회", description = "모든 영업기회 참고사항을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SalesOppNote> findSalesOppNoteList() {
        return salesOppNoteService.findsalesOppNoteList();
    }
}
