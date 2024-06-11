package com.cineverse.erpc.account.account.controller;

import com.cineverse.erpc.account.account.dto.*;
import com.cineverse.erpc.account.account.service.AccountService;
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
@RequestMapping("/account")
public class AccountController {

    private final ModelMapper modelMapper;
    private final AccountService accountService;

    @Autowired
    public AccountController(ModelMapper modelMapper, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }

    @GetMapping("/list")
    @Operation(summary = "거래처 전체 조회", description = "등록된 거래처를 전부 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<AccountDTO> findAccountLists() {

        List<AccountDTO> accountLists = accountService.findAccountLists();

        return accountLists;
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "거래처 단일조회", description = "등록된 거래처를 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseFindAccountDTO findAccountByAccountId(
            @Parameter(required = true, description = "거래처 고유번호")
            @PathVariable long accountId) {
        ResponseFindAccountDTO account =  accountService.findAccountByAccountId(accountId);

        return account;
    }

    @PostMapping("/regist")
    @Operation(summary = "거래처 등록", description = "거래처를 등록합니다")
    @ApiResponse(responseCode = "201", description = "등록성공")
    @ApiResponse(responseCode = "403", description = "입력 값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistAccountDTO> registAccount(
            @Parameter(required = true, description = "거래처 등록 요청")
            @RequestBody RequestRegistAccountDTO account) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        accountService.registAccount(accountDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistAccountDTO responseRegistAccountDTO =
                modelMapper.map(accountDTO, ResponseRegistAccountDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistAccountDTO);
    }

    @PatchMapping("/modify")
    @Operation(summary = "거래처 수정", description = "거래처 정보를 수정합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistAccountDTO> modifyAccount(
            @Parameter(required = true, description = "거래처 수정 요청")
            @RequestBody RequestRegistAccountDTO account) {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        accountService.modifyAccount(accountDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistAccountDTO responseRegistAccountDTO =
                modelMapper.map(accountDTO, ResponseRegistAccountDTO.class);

        return ResponseEntity.ok().body(responseRegistAccountDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "거래처 삭제요청", description = "거래처 삭제 요청을 보냅니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력 값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseDeleteAccount> deleteAccount(
            @Parameter(required = true, description = "거래처 삭제 요청 등록")
            @RequestBody RequestDeleteAccount requestDeleteAccount) {

        ResponseDeleteAccount responseDeleteAccount = accountService.deleteAccount(requestDeleteAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteAccount);
    }
}
