package com.cineverse.erpc.account;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.account.note.aggregate.AccountNote;
import com.cineverse.erpc.account.note.dto.RequestAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseAccountNoteRegistDTO;
import com.cineverse.erpc.account.note.dto.ResponseDeleteAccountNote;
import com.cineverse.erpc.account.note.dto.ResponseFindAllAccountNotesDTO;
import com.cineverse.erpc.account.note.repo.AccountNoteRepository;
import com.cineverse.erpc.account.note.service.AccountNoteService;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountNoteTests {

    private final AccountNoteService accountNoteService;
    private final AccountNoteRepository accountNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountNoteTests(AccountNoteService accountNoteService,
                            AccountNoteRepository accountNoteRepository,
                            EmployeeRepository employeeRepository,
                            AccountRepository accountRepository) {
        this.accountNoteService = accountNoteService;
        this.accountNoteRepository = accountNoteRepository;
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
    }

    @Test
    @Transactional
    @DisplayName("거래처 참고사항 등록 성공 테스트")
    public void successRegistAccountNote() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));
        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));

        RequestAccountNoteRegistDTO accountNote = RequestAccountNoteRegistDTO.builder()
                .employee(employee)
                .account(account)
                .accountNote("테스트 참고사항 내용")
                .build();

        ResponseAccountNoteRegistDTO testAccountNote = accountNoteService.registAccountNote(accountNote);

        assertThat(accountNote.getAccount()).isEqualTo(testAccountNote.getAccount());
        assertThat(accountNote.getEmployee()).isEqualTo(testAccountNote.getEmployee());
        assertThat(accountNote.getAccountNote()).isEqualTo(testAccountNote.getAccountNote());
        assertThat(testAccountNote.getAccountNoteDate()).isNotNull();
        assertThat(testAccountNote.getAccountDeleteDate()).isNull();
    }

    @Test
    @Transactional
    @DisplayName("거래처 참고사항 전체조회 성공 테스트")
    public void successFindAccountNotesTest() {
        List<ResponseFindAllAccountNotesDTO> accountNotes = accountNoteService.findAllAccountNotes(1);

        assertThat(accountNotes).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("거래처 참고사항 삭제 성공 테스트")
    public void successDeleteAccountNoteTest() {
        AccountNote accountNote = accountNoteRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 참고사항 입니다."));
        assertThat(accountNote.getAccountDeleteDate()).isNull();

        ResponseDeleteAccountNote testAccount = accountNoteService.deleteAccountNote(2);
        assertThat(testAccount.getAccountDeleteDate()).isNotNull();
    }
}
