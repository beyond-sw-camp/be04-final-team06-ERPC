package com.cineverse.erpc.target;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.aggregate.TeamCode;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import com.cineverse.erpc.employee.repo.TeamRepository;
import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.repository.TargetRepository;
import com.cineverse.erpc.target.service.TargetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TargetControllerTests {

    private final TargetService targetService;
    private final TargetRepository targetRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    public TargetControllerTests(TargetService targetService,
                                 TargetRepository targetRepository,
                                 EmployeeRepository employeeRepository) {
        this.targetService = targetService;
        this.targetRepository = targetRepository;
        this.employeeRepository = employeeRepository;
    }

    @Test
    @Transactional
    @DisplayName("사원별 목표 실적 조회")
    public void getTargetsByEmployee() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다"));

        List<Target> targetsFromService = targetService.getTargetsByEmployee(employee.getEmployeeId());

        List<Target> targetsFromRepo = targetRepository.findByEmployeeEmployeeId(employee.getEmployeeId());

        assertThat(targetsFromService).isNotNull();
        assertThat(targetsFromRepo).isNotNull();

        assertThat(targetsFromService).hasSameSizeAs(targetsFromRepo)
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(targetsFromRepo);

        targetsFromService.forEach(target ->
                assertThat(target.getEmployee().getEmployeeId()).isEqualTo(employee.getEmployeeId())
        );
    }

    @Test
    @Transactional
    @DisplayName("팀별 목표 실적 조회")
    public void getTargetsByTeam() {
        TeamCode teamCode = teamRepository.findById(3)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 팀입니다."));

        List<Target> expectedTargets = targetRepository.findByTeamTeamCodeId(teamCode.getTeamCodeId());

        List<Target> actualTargets = targetService.getTargetsByTeam(teamCode.getTeamCodeId());

        assertThat(actualTargets).isNotNull();

        assertThat(actualTargets).hasSameSizeAs(expectedTargets)
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(expectedTargets);

        actualTargets.forEach(target -> assertThat(target.getTeam().getTeamCodeId()).isEqualTo(teamCode.getTeamCodeId()));
    }

    @Test
    @Transactional
    @DisplayName("통합 목표 실적 조회")
    public void getIntegrateTargets() {
        List<Target> expectedTargets = targetRepository.findByEmployeeIsNullAndTeamIsNull();

        List<Target> actualTargets = targetService.getIntegrateTargets();

        assertThat(actualTargets).isNotNull();

        assertThat(actualTargets).hasSameSizeAs(expectedTargets)
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(expectedTargets);
    }
}