package com.cineverse.erpc.quotation.note;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.quotation.note.aggregate.QuotationNote;
import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseDeleteQuotationNote;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.repo.QuotationNoteRepository;
import com.cineverse.erpc.quotation.note.service.QuotationNoteService;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuotationNotesTests {
    private final QuotationNoteService quotationNoteService;
    private final EmployeeRepository employeeRepository;
    private final QuotationRepository quotationRepository;
    private final QuotationNoteRepository quotationNoteRepository;

    @Autowired
    public QuotationNotesTests(QuotationNoteService quotationNoteService,
                               EmployeeRepository employeeRepository,
                               QuotationRepository quotationRepository,
                               QuotationNoteRepository quotationNoteRepository) {
        this.quotationNoteService = quotationNoteService;
        this.employeeRepository = employeeRepository;
        this.quotationRepository = quotationRepository;
        this.quotationNoteRepository = quotationNoteRepository;
    }

    @Test
    @Transactional
    @DisplayName("견적서 참고사항 등록 테스트")
    public void successRegistQuotationNoteTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        Quotation quotation = quotationRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서 입니다."));

        RequestRegistQuotationNoteDTO requestQuotationNote = RequestRegistQuotationNoteDTO.builder()
                .quotationNote("참고사항 내용")
                .quotation(quotation)
                .employee(employee)
                .build();

        ResponseRegistQuotationNoteDTO responseQuotationNote =
                quotationNoteService.registQuotationNote(requestQuotationNote);

        assertThat(responseQuotationNote.getQuotation()).isEqualTo(requestQuotationNote.getQuotation());
        assertThat(responseQuotationNote.getEmployee()).isEqualTo(requestQuotationNote.getEmployee());
        assertThat(responseQuotationNote.getQuotationNote()).isEqualTo(requestQuotationNote.getQuotationNote());
        assertThat(responseQuotationNote.getQuotationNoteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("견적서 참고사항 전체조회 성공 테스트")
    public void successFindQuotationNotesTest() {
        List<ResponseFindAllQuotationNotesDTO> quotationNotes =
                quotationNoteService.findAllQuotationNotes(Long.valueOf(2));

        assertThat(quotationNotes).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("견적서 참고사항 삭제 성공 테스트")
    public void successDeleteQuotationNoteTest() {
        QuotationNote quotationNote = quotationNoteRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 참고사항 입니다."));

        assertThat(quotationNote.getQuotationDeleteDate()).isNull();

        ResponseDeleteQuotationNote responseDeleteQuotationNote
                = quotationNoteService.deleteQuotationNote(Long.valueOf(1));

        assertThat(responseDeleteQuotationNote.getQuotationDeleteDate()).isNotNull();
    }
}
