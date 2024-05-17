package com.cineverse.erpc.access.dto;

import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RequestAccessRequestDTO {
    private AccessRight accessRight;
    private Employee employee;
}
