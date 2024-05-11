package com.cineverse.erpc.account.service;

import com.cineverse.erpc.account.dto.AccountDTO;
import com.cineverse.erpc.account.dto.ResponseFindAccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAccountLists();

    ResponseFindAccountDTO findAccountByAccountId(long accountId);

    void registAccount(AccountDTO accountDTO);
}
