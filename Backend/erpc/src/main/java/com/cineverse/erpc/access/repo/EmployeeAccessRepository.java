package com.cineverse.erpc.access.repo;

import com.cineverse.erpc.access.aggregate.EmployeeAccess;
import com.cineverse.erpc.employee.aggregate.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeAccessRepository extends JpaRepository<EmployeeAccess, Long> {
    List<EmployeeAccess> findAllByEmployeeEmployeeId(long employeeId);

    void deleteByEmployeeEmployeeId(long employeeId);
}
