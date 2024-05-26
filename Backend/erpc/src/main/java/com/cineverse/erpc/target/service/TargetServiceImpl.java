package com.cineverse.erpc.target.service;

import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.repository.TargetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetServiceImpl implements TargetService{

    private final TargetRepository targetRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public List<Target> getTargetsByEmployee(long employeeId) {
        return targetRepository.findByEmployeeEmployeeId(employeeId);
    }

    @Override
    public List<Target> getTargetsByTeam(Integer teamCodeId) {
        return targetRepository.findByTeamTeamCodeId(teamCodeId);
    }

    @Override
    public List<Target> getIntegrateTargets() {
        return targetRepository.findByEmployeeIsNullAndTeamIsNull();
    }

    @Override
    public List<Target> getMonthlyTargets(String year, String month) {
        return targetRepository.findByTargetYearAndTargetMonth(year, month);
    }

    @Override
    public List<Target> getQuarterlyTargets(String year, String quarter) {
        return targetRepository.findByTargetYearAndTargetQuarter(year, quarter);
    }

    @Override
    public List<Target> getTargetsByYear(String year) {
        return targetRepository.findByTargetYearAndTargetQuarterIsNullAndTargetMonthIsNull(year);
    }
}
