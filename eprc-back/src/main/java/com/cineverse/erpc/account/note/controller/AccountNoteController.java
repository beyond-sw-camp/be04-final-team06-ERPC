package com.cineverse.erpc.account.note.controller;

import com.cineverse.erpc.account.note.dto.RequestAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseDeleteAccountNote;
import com.cineverse.erpc.account.note.dto.ResponseFindAllAccountNotesDTO;
import com.cineverse.erpc.account.note.dto.ResponseAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.service.AccountNoteService;
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
@RequestMapping("/account_note")
public class AccountNoteController {

    private ModelMapper modelMapper;
    private AccountNoteService accountNoteService;

    @Autowired
    public AccountNoteController(ModelMapper modelMapper, AccountNoteService accountNoteService) {
        this.modelMapper = modelMapper;
        this.accountNoteService = accountNoteService;
    }

    @PostMapping("/regist")
    @Operation(summary = "거래처 참고사항 등록", description = "거래처 참고사항을 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseAccountNoteRegistDTO> registAccountNote(
            @Parameter(required = true, description = "거래처 참고사항 등록 요청")
            @RequestBody RequestAccountNoteRegistDTO requestAccountNote) {

        accountNoteService.registAccountNote(requestAccountNote);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseAccountNoteRegistDTO responseAccountNote =
                modelMapper.map(requestAccountNote, ResponseAccountNoteRegistDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccountNote);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "거래처 참고사항 조회", description = "거래처 참고사항을 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseFindAllAccountNotesDTO> findAllAccountNotes(
            @Parameter(required = true, description = "거래처 고유번호")
            @PathVariable long accountId) {
        List<ResponseFindAllAccountNotesDTO> accountNotes =
                accountNoteService.findAllAccountNotes(accountId);

        return accountNotes;
    }

    @PatchMapping("/delete")
    @Operation(summary = "거래처 참고사항 삭제", description = "거래처 참고사항을 삭제합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseDeleteAccountNote deleteAccountNote(
            @Parameter(required = true, description = "거래처 참고사항 고유번호")
            @RequestParam long accountNoteId) {

        return accountNoteService.deleteAccountNote(accountNoteId);
    }
}
