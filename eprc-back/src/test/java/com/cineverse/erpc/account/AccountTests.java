package com.cineverse.erpc.account;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.aggregate.AccountStatus;
import com.cineverse.erpc.account.account.dto.*;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.account.account.service.AccountService;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountTests {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AccountTests(AccountService accountService,
                        AccountRepository accountRepository,
                        EmployeeRepository employeeRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("거래처 등록 성공 테스트")
    public void successRegistAccount() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setAccountStatusId(1);
        accountStatus.setAccountStatus("테스트 거래처 상태");

        AccountDTO requestRegistAccount = AccountDTO.builder()
                .accountName("테스트 거래처명")
                .corporationNum("123-93-93931")
                .accountRepresentative("테스트 거래처 대표명")
                .corporationStatus("Y")
                .accountLocation("테스트 거래처 주소")
                .accountContact("02-123-9482")
                .accountEmail("테스트 거래처 이메일")
                .accountNote("테스트 비고")
                .accountType("테스트 거래처 업종")
                .employee(employee)
                .accountStatus(accountStatus)
                .build();

        ResponseRegistAccountDTO responseRegistAccount = accountService.registAccount(requestRegistAccount);

        assertThat(requestRegistAccount.getAccountName()).isEqualTo(responseRegistAccount.getAccountName());
        assertThat(requestRegistAccount.getCorporationNum()).isEqualTo(responseRegistAccount.getCorporationNum());
        assertThat(requestRegistAccount.getAccountRepresentative())
                .isEqualTo(responseRegistAccount.getAccountRepresentative());
        assertThat(requestRegistAccount.getCorporationStatus())
                .isEqualTo(responseRegistAccount.getCorporationStatus());
        assertThat(requestRegistAccount.getAccountLocation()).isEqualTo(responseRegistAccount.getAccountLocation());
        assertThat(requestRegistAccount.getAccountContact()).isEqualTo(responseRegistAccount.getAccountContact());
        assertThat(requestRegistAccount.getAccountEmail()).isEqualTo(responseRegistAccount.getAccountEmail());
        assertThat(requestRegistAccount.getAccountNote()).isEqualTo(responseRegistAccount.getAccountNote());
        assertThat(requestRegistAccount.getAccountType()).isEqualTo(responseRegistAccount.getAccountType());
        assertThat(requestRegistAccount.getEmployee()).isEqualTo(responseRegistAccount.getEmployee());
        assertThat(requestRegistAccount.getAccountStatus()).isEqualTo(responseRegistAccount.getAccountStatus());
    }

    @Test
    @Transactional
    @DisplayName("거래처 전체조회 성공 테스트")
    public void successFindAllAccounts() {
        List<AccountDTO> accounts = accountService.findAccountLists();

        assertThat(accounts).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("거래처 단일조회 성공 테스트")
    public void successFindAccount() {
        Account testAccount = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처 입니다."));

        ResponseFindAccountDTO account = accountService.findAccountByAccountId(1);

        assertThat(account).isNotNull();
        assertThat(account.getAccountCode()).isEqualTo(testAccount.getAccountCode());
        assertThat(account.getAccountContact()).isEqualTo(testAccount.getAccountContact());
        assertThat(account.getAccountEmail()).isEqualTo(testAccount.getAccountEmail());
        assertThat(account.getAccountLocation()).isEqualTo(testAccount.getAccountLocation());
        assertThat(account.getAccountNote()).isEqualTo(testAccount.getAccountNote());
        assertThat(account.getAccountStatus()).isEqualTo(testAccount.getAccountStatus());
        assertThat(account.getAccountType()).isEqualTo(testAccount.getAccountType());
        assertThat(account.getAccountRepresentative()).isEqualTo(testAccount.getAccountRepresentative());
        assertThat(account.getEmployee()).isEqualTo(testAccount.getEmployee());
        assertThat(account.getAccountName()).isEqualTo(testAccount.getAccountName());
    }

    @Test
    @Transactional
    @DisplayName("거래처 수정 성공 테스트")
    public void successModifyAccount() {
        Account testAccount = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처 입니다."));
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setAccountStatusId(2);
        accountStatus.setAccountStatus("수정 거래처");

        AccountDTO accountDTO = AccountDTO.builder()
                .accountId(1)
                .accountName("수정 거래처명")
                .corporationNum("123-99-93931")
                .accountRepresentative("수정 거래처 대표명")
                .corporationStatus("N")
                .accountLocation("수정 거래처 주소")
                .accountContact("02-999-9482")
                .accountEmail("수정 거래처 이메일")
                .accountNote("수정 비고")
                .accountType("수정 거래처 업종")
                .accountStatus(accountStatus)
                .build();

        AccountDTO account = accountService.modifyAccount(accountDTO);

        assertThat(account.getAccountName()).isEqualTo("수정 거래처명");
        assertThat(account.getCorporationNum()).isEqualTo("123-99-93931");
        assertThat(account.getAccountRepresentative()).isEqualTo("수정 거래처 대표명");
        assertThat(account.getCorporationStatus()).isEqualTo("N");
        assertThat(account.getAccountLocation()).isEqualTo("수정 거래처 주소");
        assertThat(account.getAccountContact()).isEqualTo("02-999-9482");
        assertThat(account.getAccountEmail()).isEqualTo("수정 거래처 이메일");
        assertThat(account.getAccountNote()).isEqualTo("수정 비고");
        assertThat(account.getAccountType()).isEqualTo("수정 거래처 업종");
        assertThat(account.getAccountStatus()).isEqualTo(accountStatus);
    }

    @Test
    @Transactional
    @DisplayName("거래처 삭제요청 성공 테스트")
    public void successAccountDeleteRequest() {
        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));

        RequestDeleteAccount testDeleteRequest = RequestDeleteAccount.builder()
                .accountDeleteRequestReason("테스트 사유")
                .account(account)
                .build();

        ResponseDeleteAccount deleteRequest = accountService.deleteAccount(testDeleteRequest);

        assertThat(deleteRequest.getAccount()).isEqualTo(testDeleteRequest.getAccount());
        assertThat(deleteRequest.getAccountDeleteRequestReason())
                .isEqualTo(testDeleteRequest.getAccountDeleteRequestReason());
        assertThat(deleteRequest.getAccountDeleteRequestStatus()).isNotNull();
    }
}
