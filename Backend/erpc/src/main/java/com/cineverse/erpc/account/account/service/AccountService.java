package com.cineverse.erpc.account.account.service;

import com.cineverse.erpc.account.account.dto.AccountDTO;
import com.cineverse.erpc.account.account.dto.ResponseFindAccountDTO;
import com.cineverse.erpc.account.account.dto.ResponseRegistAccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAccountLists();

    ResponseFindAccountDTO findAccountByAccountId(long accountId);

    ResponseRegistAccountDTO registAccount(AccountDTO accountDTO);

    void modifyAccount(AccountDTO accountDTO, long accountId);
}
