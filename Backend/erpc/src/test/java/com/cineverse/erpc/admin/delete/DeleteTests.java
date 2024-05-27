package com.cineverse.erpc.admin.delete;

import com.cineverse.erpc.account.account.aggregate.AccountDeleteRequest;
import com.cineverse.erpc.account.account.repository.AccountDeleteRequestRepository;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.admin.delete.dto.account.RequestAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseFindAccoundDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.RequestOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.order.ResponseFindOrderDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.RequestQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseFindQuotationDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.order.order.aggregate.OrderDeleteRequest;
import com.cineverse.erpc.order.order.repo.OrderDeleteRequestRepository;
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
    private final AccountDeleteRequestRepository accountDeleteRequestRepository;
    private final OrderDeleteRequestRepository orderDeleteRequestRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DeleteTests(DeleteService deleteService,
                       QuotationDeleteRequestRepository quotationDeleteRequestRepository,
                       QuotationRepository quotationRepository,
                       AccountDeleteRequestRepository accountDeleteRequestRepository,
                       OrderDeleteRequestRepository orderDeleteRequestRepository,
                       AccountRepository accountRepository) {
        this.deleteService = deleteService;
        this.quotationDeleteRequestRepository = quotationDeleteRequestRepository;
        this.quotationRepository = quotationRepository;
        this.accountDeleteRequestRepository = accountDeleteRequestRepository;
        this.orderDeleteRequestRepository = orderDeleteRequestRepository;
        this.accountRepository = accountRepository;
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

    @Test
    @Transactional
    @DisplayName("거래처 삭제요청 전체조회 성공 테스트")
    public void successFindAccountDeleteRequestListTest() {
        List<ResponseAccountDeleteRequestList> accountDeleteRequestLists =
                deleteService.findAccountDeleteList();

        assertThat(accountDeleteRequestLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("거래처 삭제요청 단일조회 성공 테스트")
    public void successFindAccountDeleteRequestByIdTest() {
        AccountDeleteRequest accountDeleteRequest = accountDeleteRequestRepository.findById(Long.valueOf(3))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처 삭제요청 입니다."));

        ResponseFindAccoundDeleteRequest testAccountDeleteRequest =
                deleteService.findAccountDeleteRequestById(3);

        assertThat(accountDeleteRequest.getAccount()).isEqualTo(testAccountDeleteRequest.getAccount());

        assertThat(accountDeleteRequest.getAccountDeleteRequestReason())
                .isEqualTo(testAccountDeleteRequest.getAccountDeleteRequestReason());

        assertThat(accountDeleteRequest.getAccountDeleteRequestStatus())
                .isEqualTo(testAccountDeleteRequest.getAccountDeleteRequestStatus());
    }

    @Test
    @Transactional
    @DisplayName("거래처 삭제요청 처리 성공 테스트")
    public void successAccountDeleteRequestProcessTest() {
        RequestAccountDeleteRequestProcess accountDeleteRequestProcess
                = RequestAccountDeleteRequestProcess.builder()
                .accountDeleteRequestId(4)
                .build();

        ResponseAccountDeleteRequestProcess testAccountDeleteRequestProcess
                = deleteService.accountDeleteRequestProcess(accountDeleteRequestProcess);

        assertThat(testAccountDeleteRequestProcess.getAccountDeleteRequestStatus()).isEqualTo("Y");
        assertThat(testAccountDeleteRequestProcess.getAccount().getAccountDeleteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("수주 삭제요청 전체조회 성공 테스트")
    public void successFindOrderDeleteRequestListTest() {
        List<ResponseOrderDeleteRequestList> responseOrderDeleteRequestLists
                = deleteService.findOrderDeleteRequestList();

        assertThat(responseOrderDeleteRequestLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("수주 삭제요청 단일조회 성공 테스트")
    public void successFindOrderDeleteRequestById() {
        OrderDeleteRequest orderDeleteRequest = orderDeleteRequestRepository.findById(Long.valueOf(4))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주 삭제요청 입니다."));

        ResponseFindOrderDeleteRequest testOrderDeleteRequest
                = deleteService.findOrderDeleteRequestById(4);

        assertThat(orderDeleteRequest.getOrderDeleteRequestReason())
                .isEqualTo(testOrderDeleteRequest.getOrderDeleteRequestReason());

        assertThat(orderDeleteRequest.getOrderDeleteRequestStatus())
                .isEqualTo(testOrderDeleteRequest.getOrderDeleteRequestStatus());

        assertThat(orderDeleteRequest.getOrder()).isEqualTo(testOrderDeleteRequest.getOrder());
    }

    @Test
    @Transactional
    @DisplayName("수주 삭제요청 처리 성공 테스트")
    public void successOrderDeleteRequestProcessTest() {
        RequestOrderDeleteRequestProcess requestOrderDeleteRequestProcess
                = RequestOrderDeleteRequestProcess.builder()
                .orderDeleteRequestId(4)
                .build();

        ResponseOrderDeleteRequestProcess responseOrderDeleteRequestProcess
                = deleteService.processOrderDeleteRequest(requestOrderDeleteRequestProcess);

        assertThat(responseOrderDeleteRequestProcess.getOrderDeleteRequestStatus()).isEqualTo("Y");
        assertThat(responseOrderDeleteRequestProcess.getOrder().getOrderDeleteDate()).isNotNull();
    }
}
