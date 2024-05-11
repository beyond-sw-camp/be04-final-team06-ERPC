package com.cineverse.erpc.account.service;

import com.cineverse.erpc.account.aggregate.Account;
import com.cineverse.erpc.account.dto.AccountDTO;
import com.cineverse.erpc.account.dto.ResponseFindAccountDTO;
import com.cineverse.erpc.account.repository.AccountRepository;
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

    @Autowired
    public AccountServiceImpl(ModelMapper modelMapper, AccountRepository accountRepository) {
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDTO> findAccountLists() {
        List<Account> accounts = accountRepository.findAll();

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
    public void registAccount(AccountDTO accountDTO) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String currentDate = format.format(date);
        String accountCode;
        int random = (int) (Math.random() * 100);

        List<AccountDTO> accountList = findAccountLists();


        /* 존재하는 코드인지 확인하는 로직 구현해야 함. */
//        while (random != )

        if (0 <= random && random <= 9) {
            accountCode = "AC-" + currentDate + "00" + random;
        } else if (10 <= random && random <= 99) {
            accountCode = "AC-" + currentDate + "0" + random;
        } else {
            accountCode = "AC-" + currentDate + random;
        }

        accountDTO.setAccountCode(accountCode);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Account account = modelMapper.map(accountDTO, Account.class);

        accountRepository.save(account);
    }
}
