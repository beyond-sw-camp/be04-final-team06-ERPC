package com.cineverse.erpc.account.account.service;

import com.cineverse.erpc.account.account.dto.*;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAccountLists();

    ResponseFindAccountDTO findAccountByAccountId(long accountId);

    ResponseRegistAccountDTO registAccount(AccountDTO accountDTO);

    AccountDTO modifyAccount(AccountDTO accountDTO);

    ResponseDeleteAccount deleteAccount(RequestDeleteAccount requestDeleteAccount);
}
