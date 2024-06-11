package com.cineverse.erpc.target.repository;

import com.cineverse.erpc.target.aggregate.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetRepository extends JpaRepository<Target, Long> {

    List<Target> findByEmployeeEmployeeId(Long employeeId);

    List<Target> findByTeamTeamCodeIdAndEmployeeEmployeeIdIsNull(Integer teamCodeId);

    List<Target> findByEmployeeIsNullAndTeamIsNull();

    List<Target> findByTargetYearAndTargetMonth(String targetYear, String targetMonth);

    List<Target> findByTargetYearAndTargetQuarter(String targetYear, String targetQuarter);

    List<Target> findByTargetYearAndTargetQuarterIsNullAndTargetMonthIsNull(String targetYear);
}
