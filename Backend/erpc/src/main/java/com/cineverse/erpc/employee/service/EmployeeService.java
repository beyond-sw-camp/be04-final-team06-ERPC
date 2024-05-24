package com.cineverse.erpc.employee.service;

import com.cineverse.erpc.employee.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {
    ResponseRegistDTO registEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeDetailsByEmployeeCode(String employeeCode);

    List<ResponseEmployeesDTO> findEmployeeList();

    ResponseEmployeeDTO findEmployeeById(long employeeId);

    ResponseModifyPassword modifyPassword(RequestModifyPassword requestModifyPassword);

    ResponseModifyEmployee modifyEmployee(RequestModifyEmployee requestModifyEmployee);
}
