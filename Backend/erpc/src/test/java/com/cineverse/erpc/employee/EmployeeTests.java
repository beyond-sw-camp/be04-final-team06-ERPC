package com.cineverse.erpc.employee;

import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.employee.dto.EmployeeDTO;
import com.cineverse.erpc.employee.dto.RequestRegistDTO;
import com.cineverse.erpc.employee.dto.ResponseRegistDTO;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.employee.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeTests {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeTests(EmployeeRepository employeeRepository,
                         EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @Test
    @Transactional
    @DisplayName("사원 생성 성공 테스트")
    public void successRegistEmployeeTest() {
        TeamCode teamCode = new TeamCode();
        teamCode.setTeamCodeId(1);
        teamCode.setTeamCode("영업 1팀");

        EmployeeRank employeeRank = new EmployeeRank();
        employeeRank.setEmployeeRankId(1);
        employeeRank.setEmployeeRank("사원");

        EmployeeDTO employee = EmployeeDTO.builder()
                .employeeCode("사번")
                .employeeName("사원명")
                .employeePassword("비밀번호")
                .employeeEmail("사원 이메일")
                .employeeHp("사원 전화번호")
                .employeeNumber("내선번호")
                .employmentDate("입사일")
                .employeeRank(employeeRank)
                .teamCode(teamCode)
                .build();

        ResponseRegistDTO testEmployee = employeeService.registEmployee(employee);

        assertThat(employee.getEmployeeCode()).isEqualTo(testEmployee.getEmployeeCode());
        assertThat(employee.getEmployeeName()).isEqualTo(testEmployee.getEmployeeName());
        assertThat(employee.getEmployeeEmail()).isEqualTo(testEmployee.getEmployeeEmail());
        assertThat(employee.getEmployeeHp()).isEqualTo(testEmployee.getEmployeeHp());
        assertThat(employee.getEmployeeNumber()).isEqualTo(testEmployee.getEmployeeNumber());
        assertThat(employee.getEmploymentDate()).isEqualTo(testEmployee.getEmploymentDate());
        assertThat(employee.getEmployeeRank()).isEqualTo(testEmployee.getEmployeeRank());
        assertThat(employee.getTeamCode()).isEqualTo(testEmployee.getTeamCode());
        assertThat(testEmployee.getEmployeeUUID()).isNotNull();
        assertThat(testEmployee.getResignationDate()).isNull();

        assertThat(employee.getEmployeePassword()).isNotEqualTo(testEmployee.getEmployeePassword());
    }
}
