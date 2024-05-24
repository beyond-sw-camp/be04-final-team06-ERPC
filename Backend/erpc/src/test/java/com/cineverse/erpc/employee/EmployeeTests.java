package com.cineverse.erpc.employee;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.EmployeeRank;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.employee.dto.*;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    @Transactional
    @DisplayName("사원 전체조회 성공 테스트")
    public void successFindEmployeeListsTest() {
        List<ResponseEmployeesDTO> employees = employeeService.findEmployeeList();

        assertThat(employees).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("사원 단일조회 성공 테스트")
    public void successFindEmployeeByIdTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        ResponseEmployeeDTO testEmployee = employeeService.findEmployeeById(Long.valueOf(1));

        assertThat(employee.getEmployeeCode()).isEqualTo(testEmployee.getEmployeeCode());
        assertThat(employee.getEmployeeName()).isEqualTo(testEmployee.getEmployeeName());
        assertThat(employee.getEmployeePassword()).isEqualTo(testEmployee.getEmployeePassword());
        assertThat(employee.getEmployeeEmail()).isEqualTo(testEmployee.getEmployeeEmail());
        assertThat(employee.getEmployeeHp()).isEqualTo(testEmployee.getEmployeeHp());
        assertThat(employee.getEmployeeNumber()).isEqualTo(testEmployee.getEmployeeNumber());
        assertThat(employee.getEmploymentDate()).isEqualTo(testEmployee.getEmploymentDate());
        assertThat(employee.getEmployeeRank()).isEqualTo(testEmployee.getEmployeeRank());
        assertThat(employee.getTeamCode()).isEqualTo(testEmployee.getTeamCode());
    }

    @Test
    @Transactional
    @DisplayName("사원 비밀번호 변경 성공 테스트")
    public void successModifyEmployeePasswordTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));
        String password = employee.getEmployeePassword();

        RequestModifyPassword modifyPassword = RequestModifyPassword.builder()
                .employeeId(1)
                .employeePassword("수정 비밀번호")
                .build();

        ResponseModifyPassword response = employeeService.modifyPassword(modifyPassword);

        assertThat(modifyPassword.getEmployeePassword()).isNotEqualTo(response.getEmployeePassword());
        assertThat(password).isNotEqualTo(response.getEmployeePassword());
    }

    @Test
    @Transactional
    @DisplayName("사원 수정 성공 테스트")
    public void successModifyEmployeeTest() {
        EmployeeRank employeeRank = new EmployeeRank();
        employeeRank.setEmployeeRankId(3);
        employeeRank.setEmployeeRank("차장");

        TeamCode teamCode = new TeamCode();
        teamCode.setTeamCodeId(3);
        teamCode.setTeamCode("영업3팀");

        RequestModifyEmployee employee = RequestModifyEmployee.builder()
                .employeeId(1)
                .employeeCode("수정 테스트 사번")
                .employeeName("수정 테스트 이름")
                .employeePassword("수정 테스트 비밀번호")
                .employeeEmail("수정 테스트 이메일")
                .employeeHp("수정 테스트 번호")
                .employeeNumber("수정 테스트 내선번호")
                .employmentDate("수정 테스트 입사일")
                .resignationDate("수정 테스트 퇴사일")
                .employeeRank(employeeRank)
                .teamCode(teamCode)
                .build();

        ResponseModifyEmployee testEmployee = employeeService.modifyEmployee(employee);


        assertThat(employee.getEmployeeCode()).isEqualTo(testEmployee.getEmployeeCode());
        assertThat(employee.getEmployeeName()).isEqualTo(testEmployee.getEmployeeName());
        assertThat(employee.getEmployeeEmail()).isEqualTo(testEmployee.getEmployeeEmail());
        assertThat(employee.getEmployeeHp()).isEqualTo(testEmployee.getEmployeeHp());
        assertThat(employee.getEmployeeNumber()).isEqualTo(testEmployee.getEmployeeNumber());
        assertThat(employee.getEmploymentDate()).isEqualTo(testEmployee.getEmploymentDate());
        assertThat(employee.getResignationDate()).isEqualTo(testEmployee.getResignationDate());
        assertThat(employee.getEmployeeRank()).isEqualTo(testEmployee.getEmployeeRank());
        assertThat(employee.getTeamCode()).isEqualTo(testEmployee.getTeamCode());
    }
}
