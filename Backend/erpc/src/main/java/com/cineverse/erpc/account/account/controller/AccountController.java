package com.cineverse.erpc.account.account.controller;

import com.cineverse.erpc.account.account.dto.*;
import com.cineverse.erpc.account.account.service.AccountService;
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

    private ModelMapper modelMapper;
    private AccountService accountService;

    @Autowired
    public AccountController(ModelMapper modelMapper, AccountService accountService) {
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public List<AccountDTO> findAccountLists() {

        List<AccountDTO> accountLists = accountService.findAccountLists();

        return accountLists;
    }

    @GetMapping("/{accountId}")
    public ResponseFindAccountDTO findAccountByAccountId(@PathVariable long accountId) {
        ResponseFindAccountDTO account =  accountService.findAccountByAccountId(accountId);

        return account;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistAccountDTO> registAccount(@RequestBody RequestRegistAccountDTO account) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        accountService.registAccount(accountDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistAccountDTO responseRegistAccountDTO = modelMapper.map(accountDTO, ResponseRegistAccountDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistAccountDTO);
    }

    @PatchMapping("/modify/{accountId}")
    public ResponseEntity<ResponseRegistAccountDTO> modifyAccount(@RequestBody RequestRegistAccountDTO account,
                                                                  @PathVariable long accountId) {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        accountService.modifyAccount(accountDTO, accountId);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistAccountDTO responseRegistAccountDTO = modelMapper.map(accountDTO, ResponseRegistAccountDTO.class);

        return ResponseEntity.ok().body(responseRegistAccountDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDeleteAccount> deleteAccount(
            @RequestBody RequestDeleteAccount requestDeleteAccount) {

        ResponseDeleteAccount responseDeleteAccount = accountService.deleteAccount(requestDeleteAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteAccount);
    }
}
