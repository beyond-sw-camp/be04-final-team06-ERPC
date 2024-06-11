package com.cineverse.erpc.salesopp.opportunity.controller;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.service.SalesOppService;
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
@RequestMapping("/sales_opportunity")
public class SalesOppController {

    private final SalesOppService salesOppService;

    @Autowired
    public SalesOppController(SalesOppService salesOppService) {
        this.salesOppService = salesOppService;
    }

    @PostMapping("/regist")
    @Operation(summary = "영업기회 등록", description = "새로운 영업기회를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppDTO> registSalesOpp(
            @Parameter(description = "영업기회 데이터", required = true) @RequestBody SalesOppDTO newOpp) {
        salesOppService.registSalesOpp(newOpp);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOpp);
    }

    @PatchMapping("/modify/{salesOppId}")
    @Operation(summary = "영업기회 수정", description = "기존 영업기회를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOpp> modifySalesOpp(
            @Parameter(description = "수정할 영업기회 데이터", required = true) @RequestBody SalesOppDTO opp,
            @Parameter(description = "영업기회 ID", required = true) @PathVariable long salesOppId) {
        return ResponseEntity.ok(salesOppService.modifySalesOpp(salesOppId, opp));
    }

    @PatchMapping("/status/{salesOppId}")
    @Operation(summary = "영업기회 상태 변경", description = "영업기회의 상태를 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 변경 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOpp> changeSalesOppStatus(
            @Parameter(description = "변경할 영업기회 데이터", required = true) @RequestBody SalesOppDTO opp,
            @Parameter(description = "영업기회 ID", required = true) @PathVariable long salesOppId) {
        return ResponseEntity.ok(salesOppService.changeSalesOppStatus(salesOppId, opp));
    }

    @PostMapping("/delete")
    @Operation(summary = "영업기회 삭제 요청", description = "영업기회 삭제 요청을 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "삭제 요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<SalesOppDeleteRequestDTO> deleteSalesOpp(
            @Parameter(description = "삭제할 영업기회 데이터", required = true) @RequestBody SalesOppDeleteRequestDTO deleteOpp) {
        salesOppService.requestDeleteOpp(deleteOpp);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteOpp);
    }

    @GetMapping("")
    @Operation(summary = "영업기회 전체 조회", description = "모든 영업기회를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SalesOpp> findSalesOppList() {
        return salesOppService.findSalesOppList();
    }

    @GetMapping("/{salesOppId}")
    @Operation(summary = "영업기회 단일 조회", description = "특정 영업기회를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "영업기회를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SalesOppDTO findSalesOppById(
            @Parameter(description = "영업기회 ID", required = true) @PathVariable long salesOppId) {
        return salesOppService.findSalesOppById(salesOppId);
    }
}
