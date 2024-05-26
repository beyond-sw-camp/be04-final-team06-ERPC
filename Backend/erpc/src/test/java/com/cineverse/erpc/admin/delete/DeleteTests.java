package com.cineverse.erpc.admin.delete;

import com.cineverse.erpc.admin.delete.dto.quotation.RequestQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseFindQuotationDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationDeleteRequest;
import com.cineverse.erpc.quotation.quotation.repo.QuotationDeleteRequestRepository;
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
public class DeleteTests {
    private final DeleteService deleteService;
    private final QuotationDeleteRequestRepository quotationDeleteRequestRepository;
    private final QuotationRepository quotationRepository;

    @Autowired
    public DeleteTests(DeleteService deleteService,
                       QuotationDeleteRequestRepository quotationDeleteRequestRepository,
                       QuotationRepository quotationRepository) {
        this.deleteService = deleteService;
        this.quotationDeleteRequestRepository = quotationDeleteRequestRepository;
        this.quotationRepository = quotationRepository;
    }

    @Test
    @Transactional
    @DisplayName("견적서 삭제요청 전체조회 성공 테스트")
    public void successFindQuotationDeleteRequestListTest() {
        List<ResponseQuotationDeleteRequestList> quotationDeleteRequestLists
                = deleteService.findQuotationDeleteRequestList();

        assertThat(quotationDeleteRequestLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("견적서 삭제요청 단일조회 성공 테스트")
    public void successFindQuotationDeleteRequestByIdTest() {
        QuotationDeleteRequest quotationDeleteRequest =
                quotationDeleteRequestRepository.findById(Long.valueOf(3))
                        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 삭제요청입니다."));

        ResponseFindQuotationDeleteRequest testQuotationDeleteRequest =
                deleteService.findQuotationDeleteRequestById(Long.valueOf(3));

        assertThat(quotationDeleteRequest.getQuotationDeleteRequestReason())
                .isEqualTo(testQuotationDeleteRequest.getQuotationDeleteRequestReason());

        assertThat(quotationDeleteRequest.getQuotationDeleteRequestStatus())
                .isEqualTo(testQuotationDeleteRequest.getQuotationDeleteRequestStatus());

        assertThat(quotationDeleteRequest.getQuotation())
                .isEqualTo(testQuotationDeleteRequest.getQuotation());
    }

    @Test
    @Transactional
    @DisplayName("견적서 삭제요청 처리 성공 테스트")
    public void successQuotationDeleteRequestProcessTest() {
        Quotation quotation = quotationRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서입니다."));

        RequestQuotationDeleteRequestProcess requestQuotationDeleteRequestProcess
                = RequestQuotationDeleteRequestProcess.builder()
                .quotationDeleteRequestId(3)
                .quotation(quotation)
                .build();

        ResponseQuotationDeleteRequestProcess responseQuotationDeleteRequestProcess
                = deleteService.processQuotationDeleteRequest(requestQuotationDeleteRequestProcess);

        assertThat(responseQuotationDeleteRequestProcess.getQuotationDeleteRequestStatus()).isEqualTo("Y");
        assertThat(quotation.getQuotationDeleteDate()).isNotNull();
    }
}
