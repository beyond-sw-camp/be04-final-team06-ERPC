package com.cineverse.erpc.employee.controller;

import com.cineverse.erpc.employee.dto.*;
import com.cineverse.erpc.employee.service.EmployeeService;
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

    @GetMapping("/health_check")
    public String status() {
        return "I'm OK";
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistDTO> registEmployee(@RequestBody RequestRegistDTO employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        employeeService.registEmployee(employeeDTO);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistDTO responseRegistDTO = modelMapper.map(employeeDTO, ResponseRegistDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistDTO);
    }

    @GetMapping
    public List<ResponseEmployeesDTO> findEmployeeList() {
        return employeeService.findEmployeeList();
    }

    @GetMapping("/{employeeId}")
    public ResponseEmployeeDTO findEmployeeById(@PathVariable long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PatchMapping("/modify_password")
    private ResponseEntity<ResponseModifyPassword> modifyPassword(
            @RequestBody RequestModifyPassword requestModifyPassword) {
        ResponseModifyPassword responseModifyPassword = employeeService.modifyPassword(requestModifyPassword);

        return ResponseEntity.status(HttpStatus.OK).body(responseModifyPassword);
    }

    @PatchMapping("/modify")
    private ResponseEntity<ResponseModifyEmployee> modifyEmployee(
            @RequestBody RequestModifyEmployee requestModifyEmployee) {
        ResponseModifyEmployee responseModifyEmployee = employeeService.modifyEmployee(requestModifyEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(responseModifyEmployee);
    }

}
