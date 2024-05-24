package com.cineverse.erpc.salesopp;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.note.repository.SalesOppNoteRepository;
import com.cineverse.erpc.salesopp.note.service.SalesOppNoteService;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SaleOppNoteControllerTests {

    private final SalesOppNoteService salesOppNoteService;
    private final SalesOppRepository salesOppRepository;
    private final SalesOppNoteRepository salesOppNoteRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SaleOppNoteControllerTests(SalesOppNoteService salesOppNoteService,
                                      SalesOppRepository salesOppRepository,
                                      SalesOppNoteRepository salesOppNoteRepository,
                                      EmployeeRepository employeeRepository) {
        this.salesOppNoteService = salesOppNoteService;
        this.salesOppRepository = salesOppRepository;
        this.salesOppNoteRepository = salesOppNoteRepository;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("영업기회 참고사항 등록 테스트")
    void registSalesOppNote() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        SalesOpp salesOpp = salesOppRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회입니다."));

        SalesOppNoteDTO oppNote = SalesOppNoteDTO.builder()
                .salesOpp(salesOpp)
                .salesOppNote("영업기회 참고사항")
                .employee(employee)
                .build();

        SalesOppNote testSalesOppNote = salesOppNoteService.registSalesOppNote(oppNote);

        assertThat(oppNote.getSalesOppNote()).isEqualTo(testSalesOppNote.getSalesOppNote());
        assertThat(oppNote.getSalesOpp()).isEqualTo(testSalesOppNote.getSalesOpp());
        assertThat(oppNote.getEmployee()).isEqualTo(testSalesOppNote.getEmployee());
        assertThat(testSalesOppNote.getSalesOppNoteDate()).isNotNull();
        assertThat(testSalesOppNote.getSalesOppNoteDeleteDate()).isNull();
    }

    @Test
    @Transactional
    @DisplayName("영업기회 참고사항 수정 테스트")
    public void modifySalesOppNote() {
        SalesOppNote existingNote = salesOppNoteRepository.findById(Long.valueOf(3))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회 참고사항입니다."));

        SalesOppNoteDTO updatedNoteDTO = SalesOppNoteDTO.builder()
                .salesOppNote("수정된 참고사항 내용")
                .build();

        SalesOppNote updatedNote = salesOppNoteService.modifySalesOppNote(3, updatedNoteDTO);

        assertThat(updatedNote.getSalesOppNote()).isEqualTo(updatedNoteDTO.getSalesOppNote());
    }

    @Test
    @Transactional
    @DisplayName("영업기회 참고사항 삭제 테스트")
    void deleteSalesOppNote() {
        SalesOppNote oppNote = salesOppNoteRepository.findById(Long.valueOf(3))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회 참고사항입니다."));

        assertThat(oppNote.getSalesOppNoteDeleteDate()).isNull();

        SalesOppNote deletedNote = salesOppNoteService.deleteSalesOppNote(Long.valueOf(3));

        assertThat(deletedNote.getSalesOppNote()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("영업기회 참고사항 전체 조회 테스트")
    void findSalesOppNoteList() {
        List<SalesOppNote> salesOppNoteList = salesOppNoteService.findsalesOppNoteList();

        assertThat(salesOppNoteList).isNotEmpty();
    }
}