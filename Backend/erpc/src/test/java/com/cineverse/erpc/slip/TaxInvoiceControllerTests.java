package com.cineverse.erpc.slip;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.repository.CollectionRepository;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestRepository;
import com.cineverse.erpc.slip.taxinvoice.service.TaxInvoiceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaxInvoiceControllerTests {

    private final TaxInvoiceService taxInvoiceService;
    private final TaxInvoiceRequestRepository taxInvoiceRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    public TaxInvoiceControllerTests(TaxInvoiceService taxInvoiceService,
                                     TaxInvoiceRequestRepository taxInvoiceRequestRepository,
                                     EmployeeRepository employeeRepository,
                                     OrderRepository orderRepository,
                                     AccountRepository accountRepository,
                                     CollectionRepository collectionRepository) {
        this.taxInvoiceService = taxInvoiceService;
        this.taxInvoiceRequestRepository = taxInvoiceRequestRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.collectionRepository = collectionRepository;
    }

    @Test
    @Transactional
    @DisplayName("세금계산서 요청 등록 테스트")
    public void registTaxInvoiceRequest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        Order order = orderRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));

        Collection collection = collectionRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수금내역입니다."));


        TaxInvoiceRequestDTO testTaxInvoice = TaxInvoiceRequestDTO.builder()
                .taxInvoiceNote("테스트 세금계산서 요청 비고")
                .order(order)
                .account(account)
                .employee(employee)
                .collection(collection)
                .build();
        MultipartFile[] files = new MultipartFile[0];

        TaxInvoiceRequest request = taxInvoiceService.registTaxInvoiceRequest(testTaxInvoice, files);

        assertThat(request.getTaxInvoiceNote()).isEqualTo(testTaxInvoice.getTaxInvoiceNote());
        assertThat(request.getTaxInvoiceRequestDate()).isNotNull();
        assertThat(request.getOrder()).isEqualTo(testTaxInvoice.getOrder());
        assertThat(request.getAccount()).isEqualTo(testTaxInvoice.getAccount());
        assertThat(request.getEmployee()).isEqualTo(testTaxInvoice.getEmployee());
        assertThat(request.getCollection()).isEqualTo(testTaxInvoice.getCollection());
    }

    @Test
    @Transactional
    @DisplayName("요청한 세금계산서 전체조회 테스트")
    public void findTaxInvoiceList() {
        List<TaxInvoiceRequest> actuaRequestList = taxInvoiceRequestRepository.findAll();
        List<TaxInvoiceRequest> testList = taxInvoiceService.findTaxInvoiceList();

        assertThat(actuaRequestList.size()).isEqualTo(testList.size());
        assertThat(testList).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("요청한 세금계산서 단일조회 테스트")
    public void findTaxInvoiceRequestById() {
        TaxInvoiceRequest testRequest = taxInvoiceRequestRepository.findById(Long.valueOf(4))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 세금계산서 요청입니다."));

        TaxInvoiceRequestDTO request = taxInvoiceService.findTaxInvoiceById(Long.valueOf(4));

        assertThat(request.getTaxInvoiceNote()).isEqualTo(testRequest.getTaxInvoiceNote());
        assertThat(request.getTaxInvoiceRequestDate()).isNotNull();
        assertThat(request.getOrder()).isEqualTo(testRequest.getOrder());
        assertThat(request.getAccount()).isEqualTo(testRequest.getAccount());
        assertThat(request.getEmployee()).isEqualTo(testRequest.getEmployee());
        assertThat(request.getCollection()).isEqualTo(testRequest.getCollection());
    }

}