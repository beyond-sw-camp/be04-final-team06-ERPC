package com.cineverse.erpc.account.account.service;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.aggregate.AccountDeleteRequest;
import com.cineverse.erpc.account.account.dto.*;
import com.cineverse.erpc.account.account.repository.AccountDeleteRequestRepository;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private ModelMapper modelMapper;
    private AccountRepository accountRepository;
    private AccountDeleteRequestRepository accountDeleteRequestRepository;

    @Autowired
    public AccountServiceImpl(ModelMapper modelMapper,
                              AccountRepository accountRepository,
                              AccountDeleteRequestRepository accountDeleteRequestRepository) {
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
        this.accountDeleteRequestRepository = accountDeleteRequestRepository;
    }

    @Override
    public List<AccountDTO> findAccountLists() {
        List<Account> accounts = accountRepository.findAllByAccountDeleteDateIsNull();

        return accounts.stream().map(account -> modelMapper
                        .map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindAccountDTO findAccountByAccountId(long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty())
            throw new UsernameNotFoundException("존재하지 않는 거래처입니다.");

        ResponseFindAccountDTO responseFindAccountDTO = modelMapper.map(account, ResponseFindAccountDTO.class);

        return responseFindAccountDTO;
    }

    @Override
    @Transactional
    public ResponseRegistAccountDTO registAccount(AccountDTO accountDTO) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String currentDate = format.format(date);
        String accountCode;

        List<AccountDTO> accountList = findAccountLists();

        do {
            int random = (int) (Math.random() * 999) + 1;
            if (0 <= random && random <= 9) {
                accountCode = "AC-" + currentDate + "00" + random;
            } else if (10 <= random && random <= 99) {
                accountCode = "AC-" + currentDate + "0" + random;
            } else {
                accountCode = "AC-" + currentDate + random;
            }
        } while (isAccountCodeDuplicate(accountList, accountCode));

        accountDTO.setAccountCode(accountCode);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Account account = modelMapper.map(accountDTO, Account.class);

        accountRepository.save(account);


        return modelMapper.map(account, ResponseRegistAccountDTO.class);
    }

    private boolean isAccountCodeDuplicate(List<AccountDTO> accountList, String accountCode) {
        for (AccountDTO dto : accountList) {
            if (dto.getAccountCode().equals(accountCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public AccountDTO modifyAccount(AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountDTO.getAccountId());

        if (optionalAccount.isEmpty())
            throw new UsernameNotFoundException("존재하지 않는 거래처입니다.");

        Account account = optionalAccount.get();

        if (accountDTO.getAccountCode() != null) {
            account.setAccountCode(accountDTO.getAccountCode());
        }
        if (accountDTO.getAccountName() != null) {
            account.setAccountName(accountDTO.getAccountName());
        }
        if (accountDTO.getCorporationNum() != null) {
            account.setCorporationNum(accountDTO.getCorporationNum());
        }
        if (accountDTO.getAccountRepresentative() != null) {
            account.setAccountRepresentative(accountDTO.getAccountRepresentative());
        }
        if (accountDTO.getCorporationStatus() != null) {
            account.setCorporationStatus(accountDTO.getCorporationStatus());
        }
        if (accountDTO.getAccountLocation() != null) {
            account.setAccountLocation(accountDTO.getAccountLocation());
        }
        if (accountDTO.getAccountContact() != null) {
            account.setAccountContact(accountDTO.getAccountContact());
        }
        if (accountDTO.getAccountEmail() != null) {
            account.setAccountEmail(accountDTO.getAccountEmail());
        }
        if (accountDTO.getAccountNote() != null) {
            account.setAccountNote(accountDTO.getAccountNote());
        }
        if (accountDTO.getAccountType() != null) {
            account.setAccountType(accountDTO.getAccountType());
        }
        if (accountDTO.getEmployee() != null) {
            account.setEmployee(accountDTO.getEmployee());
        }
        if (accountDTO.getAccountStatus() != null) {
            account.setAccountStatus(accountDTO.getAccountStatus());
        }

        accountRepository.save(account);

        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public ResponseDeleteAccount deleteAccount(RequestDeleteAccount requestDeleteAccount) {
        AccountDeleteRequest accountDeleteRequest = new AccountDeleteRequest();
        accountDeleteRequest.setAccountDeleteRequestReason(requestDeleteAccount.getAccountDeleteRequestReason());
        accountDeleteRequest.setAccount(requestDeleteAccount.getAccount());
        accountDeleteRequest.setAccountDeleteRequestStatus("N");

        accountDeleteRequestRepository.save(accountDeleteRequest);

        return modelMapper.map(accountDeleteRequest, ResponseDeleteAccount.class);
    }
}
