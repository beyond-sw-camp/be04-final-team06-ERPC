package com.cineverse.erpc.target.controller;

import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {

    private final TargetService targetService;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping("/employee/{employeeId}")
    public List<Target> getTargetsByEmployee(@PathVariable long employeeId) {
        return targetService.getTargetsByEmployee(employeeId);
    }

    @GetMapping("/team/{teamCodeId}")
    public List<Target> getTargetsByTeam(@PathVariable Integer teamCodeId) {
        return targetService.getTargetsByTeam(teamCodeId);
    }

    @GetMapping("/integrate")
    public List<Target> getIntegrateTargets() {
        return targetService.getIntegrateTargets();
    }

    @GetMapping("/monthly")
    public List<Target> getMonthlyTargets(@RequestParam String year, @RequestParam String month) {
        return targetService.getMonthlyTargets(year, month);
    }

    @GetMapping("/quarterly")
    public List<Target> getQuarterlyTargets(@RequestParam String year, @RequestParam String quarter) {
        return targetService.getQuarterlyTargets(year, quarter);
    }

    @GetMapping("/by-category")
    public List<Target> getTargetsByYear(@RequestParam String year) {
        return targetService.getTargetsByYear(year);
    }
}
