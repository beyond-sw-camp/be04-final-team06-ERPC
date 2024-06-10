package com.cineverse.erpc.access.dto;

import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

import java.util.List;

@Data
public class RequestAddAccessDTO {
    private Employee employee;
    private List<AccessRight> accessRight;
}
