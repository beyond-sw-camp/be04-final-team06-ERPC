package com.cineverse.erpc.salesopp;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppStatus;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import com.cineverse.erpc.salesopp.opportunity.service.SalesOppService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesOppControllerTests {

    private final SalesOppService salesOppService;
    private final SalesOppRepository salesOppRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalesOppControllerTests(SalesOppService salesOppService,
                                   SalesOppRepository salesOppRepository,
                                   EmployeeRepository employeeRepository) {
        this.salesOppService = salesOppService;
        this.salesOppRepository = salesOppRepository;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("영업기회 등록 테스트")
    public void registSalesOpp() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        SalesOppDTO testOpp = SalesOppDTO.builder()
                .oppAccountName("(테스트) 영업기회 거래처 사업자명")
                .oppAccountPic("(테스트) 영업기회 거래처 담당자")
                .oppAccountContact("(테스트) 영업기회 거래처 연락처")
                .oppAccountLocation("(테스트) 영업기회 거래처 주소")
                .oppAccountEmail("(테스트) 영업기회 거래처 이메일")
                .oppAccountNote("(테스트) 영업기회 거래처 비고")
                .employee(employee)
                .build();

        SalesOpp registeredOpp = salesOppService.registSalesOpp(testOpp);

        assertThat(registeredOpp.getOppAccountName()).isEqualTo(testOpp.getOppAccountName());
        assertThat(registeredOpp.getOppAccountPic()).isEqualTo(testOpp.getOppAccountPic());
        assertThat(registeredOpp.getOppAccountContact()).isEqualTo(testOpp.getOppAccountContact());
        assertThat(registeredOpp.getOppAccountLocation()).isEqualTo(testOpp.getOppAccountLocation());
        assertThat(registeredOpp.getOppAccountEmail()).isEqualTo(testOpp.getOppAccountEmail());
        assertThat(registeredOpp.getOppAccountNote()).isEqualTo(testOpp.getOppAccountNote());
        assertThat(registeredOpp.getEmployee()).isEqualTo(employee);
    }

    @Test
    @Transactional
    @DisplayName("영업기회 수정 테스트")
    public void modifySalesOpp() {
        SalesOpp existingOpp = salesOppRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회입니다."));

        SalesOppDTO updatedOpp = SalesOppDTO.builder()
                .oppAccountName("(수정) 영업기회 거래처 사업자명")
                .oppAccountPic("(수정) 영업기회 거래처 담당자")
                .oppAccountContact("(수정) 영업기회 거래처 연락처")
                .oppAccountLocation("(수정) 영업기회 거래처 주소")
                .oppAccountEmail("(수정) 영업기회 거래처 이메일")
                .oppAccountNote("(수정) 영업기회 거래처 비고")
                .employee(existingOpp.getEmployee())
                .build();

        SalesOpp updatedSalesOpp = salesOppService.modifySalesOpp(existingOpp.getSalesOppId(), updatedOpp);

        assertThat(updatedSalesOpp.getOppAccountName()).isEqualTo(updatedOpp.getOppAccountName());
        assertThat(updatedSalesOpp.getOppAccountPic()).isEqualTo(updatedOpp.getOppAccountPic());
        assertThat(updatedSalesOpp.getOppAccountContact()).isEqualTo(updatedOpp.getOppAccountContact());
        assertThat(updatedSalesOpp.getOppAccountLocation()).isEqualTo(updatedOpp.getOppAccountLocation());
        assertThat(updatedSalesOpp.getOppAccountEmail()).isEqualTo(updatedOpp.getOppAccountEmail());
        assertThat(updatedSalesOpp.getOppAccountNote()).isEqualTo(updatedOpp.getOppAccountNote());
        assertThat(updatedSalesOpp.getEmployee()).isEqualTo(updatedOpp.getEmployee());
    }

    @Test
    @Transactional
    @DisplayName("영업기회 상태 변경 테스트")
    public void changeSalesOppStatusIdTest() {

        SalesOppStatus newStatus = new SalesOppStatus();
        newStatus.setSalesOppStatusId(2);

        SalesOppDTO testOpp = SalesOppDTO.builder()
                .salesOppStatus(newStatus)
                .build();

        SalesOpp updatedOpp = salesOppService.changeSalesOppStatus(2, testOpp);

        assertThat(updatedOpp.getSalesOppStatus().getSalesOppStatusId()).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("영업기회 삭제 요청 성공 테스트")
    public void successSalesOppDeleteRequest() {

        SalesOpp salesOpp = salesOppRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회입니다."));

        SalesOppDeleteRequestDTO requestDeleteOpp = SalesOppDeleteRequestDTO.builder()
                .salesOppDeleteRequestReason("테스트 삭제 사유")
                .salesOpp(salesOpp)
                .build();

        SalesOppDeleteRequest deletedOpp = salesOppService.requestDeleteOpp(requestDeleteOpp);

        assertThat(requestDeleteOpp.getSalesOppDeleteRequestReason()).isEqualTo(deletedOpp.getSalesOppDeleteRequestReason());
        assertThat(requestDeleteOpp.getSalesOpp()).isEqualTo(deletedOpp.getSalesOpp());
    }

    @Test
    @Transactional
    @DisplayName("영업기회 전체 조회 테스트")
    public void findSalesOppList() {
        List<SalesOpp> testOppList = salesOppService.findSalesOppList();

        assertThat(testOppList).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("영업기회 단일 조회 테스트")
    public void findSalesOppById() {
        SalesOpp testOpp = salesOppRepository.findById(Long.valueOf(2))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 영업기회입니다."));

        SalesOppDTO salesOpp = salesOppService.findSalesOppById(2);

        assertThat(salesOpp.getOppAccountName()).isEqualTo(testOpp.getOppAccountName());
        assertThat(salesOpp.getOppAccountPic()).isEqualTo(testOpp.getOppAccountPic());
        assertThat(salesOpp.getOppAccountContact()).isEqualTo(testOpp.getOppAccountContact());
        assertThat(salesOpp.getOppAccountLocation()).isEqualTo(testOpp.getOppAccountLocation());
        assertThat(salesOpp.getOppAccountEmail()).isEqualTo(testOpp.getOppAccountEmail());
        assertThat(salesOpp.getOppAccountNote()).isEqualTo(testOpp.getOppAccountNote());
        assertThat(salesOpp.getEmployee()).isEqualTo(testOpp.getEmployee());
    }
}