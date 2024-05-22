package com.cineverse.erpc.account;

import com.cineverse.erpc.account.account.aggregate.AccountStatus;
import com.cineverse.erpc.account.account.dto.AccountDTO;
import com.cineverse.erpc.account.account.dto.RequestRegistAccountDTO;
import com.cineverse.erpc.account.account.dto.ResponseRegistAccountDTO;
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
                .accountRepresentitive("테스트 거래처 대표명")
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
        assertThat(requestRegistAccount.getAccountRepresentitive())
                .isEqualTo(responseRegistAccount.getAccountRepresentitive());
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
}
