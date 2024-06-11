package com.cineverse.erpc.quotation.note.controller;

import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseDeleteQuotationNote;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.service.QuotationNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotation_note")
public class QuotationNoteController {
    private ModelMapper mapper;
    private QuotationNoteService quotationNoteService;

    @Autowired
    public QuotationNoteController(ModelMapper mapper, QuotationNoteService quotationNoteService) {
        this.mapper = mapper;
        this.quotationNoteService = quotationNoteService;
    }

    @PostMapping("/regist")
    @Operation(summary = "견적서 참고사항 등록", description = "견적서 참고사항을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistQuotationNoteDTO> registQuotationNote(
            @Parameter(required = true, description = "견적서 참고사항 등록 요청")
            @RequestBody RequestRegistQuotationNoteDTO requestQuotationNote) {

        quotationNoteService.registQuotationNote(requestQuotationNote);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistQuotationNoteDTO responseQuotationNote =
                mapper.map(requestQuotationNote, ResponseRegistQuotationNoteDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseQuotationNote);
    }

    @GetMapping("/{quotationId}")
    @Operation(summary = "견적서 참고사항 조회", description = "견적서 참고사항을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(
            @Parameter(required = true, description = "견적서 고유번호")
            @PathVariable long quotationId) {
        List<ResponseFindAllQuotationNotesDTO> quotationNotes =
                quotationNoteService.findAllQuotationNotes(quotationId);

        return quotationNotes;
    }

    @PatchMapping("/delete")
    @Operation(summary = "견적서 참고사항 삭제", description = "견적서 참고사항을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseDeleteQuotationNote> deleteQuotationNote(
            @Parameter(required = true, description = "견적서 참고사항 고유번호")
            @RequestParam long quotationNoteId) {

        ResponseDeleteQuotationNote responseDeleteQuotationNote =
                quotationNoteService.deleteQuotationNote(quotationNoteId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDeleteQuotationNote);
    }

}
