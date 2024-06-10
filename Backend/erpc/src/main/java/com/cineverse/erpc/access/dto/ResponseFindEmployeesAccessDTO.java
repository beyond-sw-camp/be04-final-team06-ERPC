package com.cineverse.erpc.access.dto;

import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseFindEmployeesAccessDTO {
    private Employee employee;
    private AccessRight accessRight;
}
