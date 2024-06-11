package com.cineverse.erpc.quotation;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.repository.ProductRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.quotation.quotation.dto.*;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import com.cineverse.erpc.warehouse.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuotationTests {
    private final EmployeeRepository employeeRepository;
    private final QuotationRepository quotationRepository;
    private final QuotationService quotationService;
    private final AccountRepository accountRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public QuotationTests(EmployeeRepository employeeRepository,
                          QuotationRepository quotationRepository,
                          QuotationService quotationService,
                          AccountRepository accountRepository,
                          WarehouseRepository warehouseRepository,
                          ProductRepository productRepository) {
        this.employeeRepository = employeeRepository;
        this.quotationRepository = quotationRepository;
        this.quotationService = quotationService;
        this.accountRepository = accountRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    @Test
    @Transactional
    @DisplayName("견적서 단일조회 성공 테스트")
    public void successFindQuotationTest() {
        Quotation quotation = quotationService.findQuotationById(Long.valueOf(2));

        assertThat(quotation.getQuotationId()).isEqualTo(2);
        assertThat(quotation.getQuotationDate()).isNotNull();
        assertThat(quotation.getQuotationDueDate()).isNotNull();
        assertThat(quotation.getQuotationCode()).isNotNull();
        assertThat(quotation.getEmployee()).isNotNull();
        assertThat(quotation.getAccount()).isNotNull();
        assertThat(quotation.getTransaction()).isNotNull();
        for (int i = 0; i < quotation.getQuotationProduct().size(); i++) {
            assertThat(quotation.getQuotationProduct().get(i)).isNotNull();
        }
    }

    @Test
    @Transactional
    @DisplayName("견적서 전체조회 성공 테스트")
    public void successFindQuotationListTest() {
        List<QuotationDTO> quotations = quotationService.findAllQuotations();

        assertThat(quotations).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("견적서 등록 성공 테스트")
    public void successRegistQuotationTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));
        Account account = accountRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));
        Warehouse warehouse = warehouseRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 창고입니다."));

        Product product = productRepository.findAll().get(0);
        Product product2 = productRepository.findAll().get(1);

        QuotationProduct quotationProduct = new QuotationProduct();
        quotationProduct.setProduct(product);
        quotationProduct.setQuotationSupplyPrice(10000);
        quotationProduct.setQuotationProductCount(10);
        quotationProduct.setQuotationProductionNote("참고사항");

        QuotationProduct quotationProduct2 = new QuotationProduct();
        quotationProduct2.setProduct(product2);
        quotationProduct2.setQuotationSupplyPrice(10000);
        quotationProduct2.setQuotationProductCount(10);
        quotationProduct2.setQuotationProductionNote("참고사항");

        List<QuotationProduct> quotationProducts = new ArrayList<>();
        quotationProducts.add(quotationProduct);
        quotationProducts.add(quotationProduct2);

        MultipartFile[] files = new MultipartFile[0];

        RequestRegistQuotationDTO quotation = RequestRegistQuotationDTO.builder()
                .quotationNote("견적서 내용")
                .quotationTotalCost(100000)
                .quotationDueDate("납기일자")
                .employee(employee)
                .account(account)
                .warehouse(warehouse)
                .quotationProduct(quotationProducts)
                .build();

        Quotation testQuotation = quotationService.registQuotation(quotation, files);

        assertThat(testQuotation.getQuotationNote()).isEqualTo(quotation.getQuotationNote());
        assertThat(testQuotation.getQuotationTotalCost()).isEqualTo(quotation.getQuotationTotalCost());
        assertThat(testQuotation.getQuotationDueDate()).isEqualTo(quotation.getQuotationDueDate());
        assertThat(testQuotation.getEmployee()).isEqualTo(quotation.getEmployee());
        assertThat(testQuotation.getAccount()).isEqualTo(quotation.getAccount());
        assertThat(testQuotation.getWarehouse()).isEqualTo(quotation.getWarehouse());

        for (int i = 0; i < quotation.getQuotationProduct().size(); i++) {
            assertThat(testQuotation.getQuotationProduct().get(i))
                    .isEqualTo(quotation.getQuotationProduct().get(i));
        }

        assertThat(testQuotation.getTransaction()).isNotNull();
        assertThat(testQuotation.getQuotationDate()).isNotNull();
        assertThat(testQuotation.getQuotationCode()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("견적서 수정 성공 테스트")
    public void successModifyQuotationTest() {
        Product product = productRepository.findAll().get(0);
        Product product2 = productRepository.findAll().get(1);

        QuotationProduct quotationProduct = new QuotationProduct();
        quotationProduct.setProduct(product);
        quotationProduct.setQuotationSupplyPrice(100000);
        quotationProduct.setQuotationProductCount(10);
        quotationProduct.setQuotationProductionNote("수정 참고사항");

        QuotationProduct quotationProduct2 = new QuotationProduct();
        quotationProduct2.setProduct(product2);
        quotationProduct2.setQuotationSupplyPrice(100000);
        quotationProduct2.setQuotationProductCount(10);
        quotationProduct2.setQuotationProductionNote("수정 참고사항");

        List<QuotationProduct> quotationProducts = new ArrayList<>();
        quotationProducts.add(quotationProduct);
        quotationProducts.add(quotationProduct2);

        MultipartFile[] files = new MultipartFile[0];

        RequestModifyQuotationDTO quotation = RequestModifyQuotationDTO.builder()
                .quotationId(6)
                .quotationNote("견적서 수정 내용")
                .quotationTotalCost(10000000)
                .quotationDueDate("수정 납기일자")
                .quotationProduct(quotationProducts)
                .build();

        ResponseModifyQuotationDTO testQuotation = quotationService.modifyQuotation(quotation, files);

        assertThat(testQuotation.getQuotationNote()).isEqualTo(quotation.getQuotationNote());
        assertThat(testQuotation.getQuotationTotalCost()).isEqualTo(quotation.getQuotationTotalCost());
        assertThat(testQuotation.getQuotationDueDate()).isEqualTo(quotation.getQuotationDueDate());

        for (int i = 0; i < quotation.getQuotationProduct().size(); i++) {
            QuotationProduct expectedProduct = quotation.getQuotationProduct().get(i);
            QuotationProduct actualProduct = testQuotation.getQuotationProduct().get(i);

            assertThat(actualProduct.getProduct().getProductId())
                    .isEqualTo(expectedProduct.getProduct().getProductId());
        }
        assertThat(testQuotation.getTransaction()).isNotNull();
        assertThat(testQuotation.getQuotationDate()).isNotNull();
        assertThat(testQuotation.getQuotationCode()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("견적서 삭제요청 성공 테스트")
    public void successQuotationDeleteRequestTest() {
        Quotation quotation = quotationRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서입니다."));

        RequestDeleteQuotation requestDeleteQuotation = RequestDeleteQuotation.builder()
                .quotation(quotation)
                .quotationDeleteRequestReason("삭제사유")
                .build();

        ResponseDeleteQuotation responseDeleteQuotation = quotationService.deleteQuotation(requestDeleteQuotation);

        assertThat(responseDeleteQuotation.getQuotationDeleteRequestReason())
                .isEqualTo(requestDeleteQuotation.getQuotationDeleteRequestReason());
        assertThat(responseDeleteQuotation.getQuotation())
                .isEqualTo(requestDeleteQuotation.getQuotation());

        assertThat(responseDeleteQuotation.getQuotationDeleteRequestStatus()).isNotNull();
    }
}
