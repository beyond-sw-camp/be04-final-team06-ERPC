package com.cineverse.erpc.employee.controller;

import com.cineverse.erpc.employee.dto.*;
import com.cineverse.erpc.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private Environment env;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(Environment env,
                              ModelMapper modelMapper,
                              EmployeeService employeeService) {
        this.env = env;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    @PostMapping("/regist")
    @Operation(summary = "사원 등록", description = "사원을 등록합니다")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEntity<ResponseRegistDTO> registEmployee(
            @Parameter(required = true, description = "사원등록 요청")
            @RequestBody RequestRegistDTO employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        employeeService.registEmployee(employeeDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistDTO responseRegistDTO = modelMapper.map(employeeDTO, ResponseRegistDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistDTO);
    }

    @GetMapping
    @Operation(summary = "전체 사원 조회", description = "전체 사원을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public List<ResponseEmployeesDTO> findEmployeeList() {
        return employeeService.findEmployeeList();
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "사원 단일조회", description = "사원을 단일조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public ResponseEmployeeDTO findEmployeeById(
            @Parameter(required = true, description = "사원 고유번호")
            @PathVariable long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PatchMapping("/modify_password")
    @Operation(summary = "사원 비밀번호 수정", description = "사원 비밀번호를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    private ResponseEntity<ResponseModifyPassword> modifyPassword(
            @Parameter(required = true, description = "비밀번호 변경 요청")
            @RequestBody RequestModifyPassword requestModifyPassword) {
        ResponseModifyPassword responseModifyPassword = employeeService.modifyPassword(requestModifyPassword);

        return ResponseEntity.status(HttpStatus.OK).body(responseModifyPassword);
    }

    @PatchMapping("/modify")
    @Operation(summary = "사원 정보 수정", description = "사원의 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    private ResponseEntity<ResponseModifyEmployee> modifyEmployee(
            @Parameter(required = true, description = "사원 정보수정 요청")
            @RequestBody RequestModifyEmployee requestModifyEmployee) {
        ResponseModifyEmployee responseModifyEmployee = employeeService.modifyEmployee(requestModifyEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(responseModifyEmployee);
    }



}
