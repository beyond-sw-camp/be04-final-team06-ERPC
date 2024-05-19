package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.dto.EmployeeDTO;
import com.cineverse.erpc.employee.dto.ResponseEmployeeDTO;
import com.cineverse.erpc.employee.dto.ResponseEmployeesDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
    void registEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeDetailsByEmployeeCode(String employeeCode);

    List<ResponseEmployeesDTO> findEmployeeList();

    ResponseEmployeeDTO findEmployeeById(long employeeId);
}
