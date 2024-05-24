package com.cineverse.erpc.target.service;

import com.cineverse.erpc.target.aggregate.Target;

import java.util.List;

public interface TargetService {
    List<Target> getTargetsByEmployee(long employeeId);
    List<Target> getTargetsByTeam(Integer teamCodeId);
    List<Target> getIntegrateTargets();
    List<Target> getMonthlyTargets(String year, String month);
    List<Target> getQuarterlyTargets(String year, String quarter);
    List<Target> getTargetsByYear(String year);
}
