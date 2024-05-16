package com.cineverse.erpc.target.controller;

import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {

    private final TargetService targetService;

    @Autowired
    public TargetController(TargetService targetService) {
        this.targetService = targetService;
    }

    /* 개인별 실적 조회 */
    @GetMapping("/employee/{employeeId}")
    public List<Target> getTotalTargetList() {
        List<Target> targetList = targetService.findTotalTarget();

        return targetList;
    }

    /* 팀별 실적 조회 */

    /* 통합 실적 조회 */
}
