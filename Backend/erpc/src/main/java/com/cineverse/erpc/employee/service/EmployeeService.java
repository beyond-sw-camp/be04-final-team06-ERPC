package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.dto.EmployeeDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface EmployeeService extends UserDetailsService {
    void registEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeDetailsByEmployeeCode(String employeeCode);
}
