package com.cineverse.erpc.approval;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.dto.contract.*;
import com.cineverse.erpc.approval.dto.quotation.*;
import com.cineverse.erpc.approval.dto.shipment.*;
import com.cineverse.erpc.approval.repo.ContractApprovalRepository;
import com.cineverse.erpc.approval.repo.QuotationApprovalRepository;
import com.cineverse.erpc.approval.repo.ShipmentApprovalRepository;
import com.cineverse.erpc.approval.service.ApprovalService;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApprovalTests {
    private final ApprovalService approvalService;
    private final ContractApprovalRepository contractApprovalRepository;
    private final QuotationApprovalRepository quotationApprovalRepository;
    private final ShipmentApprovalRepository shipmentApprovalRepository;
    private final QuotationRepository quotationRepository;
    private final ContractRepository contractRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ApprovalTests(ApprovalService approvalService,
                         ContractApprovalRepository contractApprovalRepository,
                         QuotationApprovalRepository quotationApprovalRepository,
                         ShipmentApprovalRepository shipmentApprovalRepository,
                         QuotationRepository quotationRepository,
                         ContractRepository contractRepository,
                         OrderRepository orderRepository) {
        this.approvalService = approvalService;
        this.contractApprovalRepository = contractApprovalRepository;
        this.quotationApprovalRepository = quotationApprovalRepository;
        this.shipmentApprovalRepository = shipmentApprovalRepository;
        this.quotationRepository = quotationRepository;
        this.contractRepository = contractRepository;
        this.orderRepository = orderRepository;
    }

    @Test
    @Transactional
    @DisplayName("견적서 결재요청 성공 테스트")
    public void successQuotationApprovalRequestTest() {
        Quotation quotation = quotationRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서입니다."));

        RequestRegistQuotationApproval approval = RequestRegistQuotationApproval.builder()
                .quotation(quotation)
                .build();

        ResponseRegistQuotationApproval testApproval =
                approvalService.registQuotationtApproval(approval);

        assertThat(approval.getQuotation()).isEqualTo(testApproval.getQuotation());
        assertThat(testApproval.getApprovalContent()).isNull();
        assertThat(testApproval.getApprovalDate()).isNull();
        assertThat(testApproval.getApprovalStatus().getApprovalStatusId()).isEqualTo(1);
        assertThat(testApproval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("계약서 결재요청 성공 테스트")
    public void successContractApprovalRequestTest() {
        Contract contract = contractRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계약서입니다."));

        RequestRegistContractApproval approval = RequestRegistContractApproval.builder()
                .contract(contract)
                .build();

        ResponseRegistContractApproval testApproval =
                approvalService.registContractApproval(approval);

        assertThat(approval.getContract()).isEqualTo(testApproval.getContract());
        assertThat(testApproval.getApprovalContent()).isNull();
        assertThat(testApproval.getApprovalDate()).isNull();
        assertThat(testApproval.getApprovalStatus().getApprovalStatusId()).isEqualTo(1);
        assertThat(testApproval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("수주 결재요청 성공 테스트")
    public void successShipmentApprovalRequestTest() {
        Order order = orderRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        RequestRegistShipmentApproval approval = RequestRegistShipmentApproval.builder()
                .order(order)
                .build();

        ResponseRegistShipmentApproval testApproval =
                approvalService.registShipmentApproval(approval);

        assertThat(approval.getOrder()).isEqualTo(testApproval.getOrder());
        assertThat(testApproval.getApprovalContent()).isNull();
        assertThat(testApproval.getApprovalDate()).isNull();
        assertThat(testApproval.getApprovalStatus().getApprovalStatusId()).isEqualTo(1);
        assertThat(testApproval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("견적서 결재 전체조회 성공 테스트")
    public void successFindQuotationApprovalListsTest() {
        List<ResponseQuotationApprovalList> approvalLists = approvalService.findQuotationApprovalList();

        assertThat(approvalLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("계약서 결재 전체조회 성공 테스트")
    public void successFindContractApprovalListsTest() {
        List<ResponseContractApprovalList> approvalLists = approvalService.findContractApprovalList();

        assertThat(approvalLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("수주 결재 전체조회 성공 테스트")
    public void successFindShipmentApprovalListsTest() {
        List<ResponseShipmentApprovalList> approvalLists = approvalService.findShipmentApprovalList();

        assertThat(approvalLists).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("견적서 결재 단일조회 성공 테스트")
    public void successFindQuotationApprovalTest() {
        ResponseFindQuotationApproval approval = approvalService.findQuotationApproval(3);

        assertThat(approval.getQuotation()).isNotNull();
        assertThat(approval.getApprovalStatus()).isNotNull();
        assertThat(approval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("계약서 결재 단일조회 성공 테스트")
    public void successFindContractApprovalTest() {
        ResponseFindContractApproval approval = approvalService.findContractApproval(2);

        assertThat(approval.getContract()).isNotNull();
        assertThat(approval.getApprovalStatus()).isNotNull();
        assertThat(approval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("수주 결재 단일조회 성공 테스트")
    public void successFindShipmentApprovalTest() {
        ResponseFindShipmentApproval approval = approvalService.findShipmentApproval(2);

        assertThat(approval.getOrder()).isNotNull();
        assertThat(approval.getApprovalStatus()).isNotNull();
        assertThat(approval.getApprovalRequestDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("견적서 결재처리 성공 테스트")
    public void successQuotationApprovalProcessTest() {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(2);
        approvalStatus.setApprovalStatus("승인");

        RequestQuotationApprovalProcess approvalProcess = RequestQuotationApprovalProcess.builder()
                .quotationApprovalId(3)
                .approvalContent("결제 내용")
                .approvalStatus(approvalStatus)
                .build();

        ResponseQuotationApprovalProcess testApprovalProcess =
                approvalService.quotationApprovalProcess(approvalProcess);

        assertThat(approvalProcess.getApprovalContent()).isEqualTo(testApprovalProcess.getApprovalContent());
        assertThat(approvalProcess.getApprovalStatus()).isEqualTo(testApprovalProcess.getApprovalStatus());
    }

    @Test
    @Transactional
    @DisplayName("계약서 결재처리 성공 테스트")
    public void successContractApprovalProcessTest() {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(2);
        approvalStatus.setApprovalStatus("승인");

        RequestContractApprovalProcess approvalProcess = RequestContractApprovalProcess.builder()
                .contractApprovalId(2)
                .approvalContent("결제 내용")
                .approvalStatus(approvalStatus)
                .build();

        ResponseContractApprovalProcess testApprovalProcess =
                approvalService.contractApprovalProcess(approvalProcess);

        assertThat(approvalProcess.getApprovalContent()).isEqualTo(testApprovalProcess.getApprovalContent());
        assertThat(approvalProcess.getApprovalStatus()).isEqualTo(testApprovalProcess.getApprovalStatus());
    }

    @Test
    @Transactional
    @DisplayName("수주 결재처리 성공 테스트")
    public void successShipmentApprovalProcessTest() {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(2);
        approvalStatus.setApprovalStatus("승인");

        RequestShipmentApprovalProcess approvalProcess = RequestShipmentApprovalProcess.builder()
                .shipmentApprovalId(2)
                .approvalContent("결제 내용")
                .approvalStatus(approvalStatus)
                .build();

        ResponseShipmentApprovalProcess testApprovalProcess =
                approvalService.shipmentApprovalProcess(approvalProcess);

        assertThat(approvalProcess.getApprovalContent()).isEqualTo(testApprovalProcess.getApprovalContent());
        assertThat(approvalProcess.getApprovalStatus()).isEqualTo(testApprovalProcess.getApprovalStatus());
    }
}
