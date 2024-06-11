package com.cineverse.erpc.target.controller;

import com.cineverse.erpc.target.aggregate.Target;
import com.cineverse.erpc.target.service.TargetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "직원별 목표 조회", description = "특정 직원의 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "직원을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getTargetsByEmployee(
            @Parameter(description = "직원 ID", required = true) @PathVariable long employeeId) {
        return targetService.getTargetsByEmployee(employeeId);
    }

    @GetMapping("/team/{teamCodeId}")
    @Operation(summary = "팀별 목표 조회", description = "특정 팀의 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "팀을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getTargetsByTeam(
            @Parameter(description = "팀 코드 ID", required = true) @PathVariable Integer teamCodeId) {
        return targetService.getTargetsByTeam(teamCodeId);
    }

    @GetMapping("/integrate")
    @Operation(summary = "통합 목표 조회", description = "통합된 모든 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getIntegrateTargets() {
        return targetService.getIntegrateTargets();
    }

    @GetMapping("/monthly")
    @Operation(summary = "월별 목표 조회", description = "특정 연도와 월의 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getMonthlyTargets(
            @Parameter(description = "연도", required = true) @RequestParam String year,
            @Parameter(description = "월", required = true) @RequestParam String month) {
        return targetService.getMonthlyTargets(year, month);
    }

    @GetMapping("/quarterly")
    @Operation(summary = "분기별 목표 조회", description = "특정 연도와 분기의 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getQuarterlyTargets(
            @Parameter(description = "연도", required = true) @RequestParam String year,
            @Parameter(description = "분기", required = true) @RequestParam String quarter) {
        return targetService.getQuarterlyTargets(year, quarter);
    }

    @GetMapping("/by-category")
    @Operation(summary = "연도별 카테고리 목표 조회", description = "특정 연도의 카테고리별 목표를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<Target> getTargetsByYear(
            @Parameter(description = "연도", required = true) @RequestParam String year) {
        return targetService.getTargetsByYear(year);
    }
}
