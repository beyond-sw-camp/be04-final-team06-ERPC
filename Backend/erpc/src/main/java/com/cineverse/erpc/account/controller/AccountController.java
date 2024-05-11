package com.cineverse.erpc.account.controller;

import com.cineverse.erpc.account.dto.AccountDTO;
import com.cineverse.erpc.account.dto.RequestRegistAccountDTO;
import com.cineverse.erpc.account.dto.ResponseFindAccountDTO;
import com.cineverse.erpc.account.dto.ResponseRegistAccountDTO;
import com.cineverse.erpc.account.service.AccountService;
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
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        accountService.registAccount(accountDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistAccountDTO responseRegistAccountDTO = modelMapper.map(accountDTO, ResponseRegistAccountDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistAccountDTO);
    }
}
