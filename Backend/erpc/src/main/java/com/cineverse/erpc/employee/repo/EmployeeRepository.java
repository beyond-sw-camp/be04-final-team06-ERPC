package com.cineverse.erpc.employee.repo;

import com.cineverse.erpc.employee.aggregate.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmployeeCode(String employeeCode);
}
