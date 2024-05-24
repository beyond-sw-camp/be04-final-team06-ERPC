package com.cineverse.erpc.sales;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.employee.repo.TeamRepository;
import com.cineverse.erpc.sales.service.SalesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SalesControllerTests {

    private final SalesService salesService;
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public SalesControllerTests(SalesService salesService,
                                EmployeeRepository employeeRepository,
                                TeamRepository teamRepository) {
        this.salesService = salesService;
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    @Test
    @Transactional
    @DisplayName("사원별 실적 조회 테스트")
    public void getEmployeeSales() {

        Employee employee = employeeRepository.findById(Long.valueOf(1)).
                orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        Long sales = salesService.calculateIndividualSales(employee.getEmployeeId());
        assertThat(sales).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("팀별 실적 조회 테스트")
    public void getTeamSales() {

        TeamCode teamCode = teamRepository.findById(3)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀입니다."));

        Long sales = salesService.calculateTeamSales(teamCode.getTeamCodeId());
        assertThat(sales).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("통합 실적 조회 테스트")
    public void getTotalSales() {
        Long sales = salesService.calculateTotalSales();
        assertThat(sales).isNotNull();
    }
}
