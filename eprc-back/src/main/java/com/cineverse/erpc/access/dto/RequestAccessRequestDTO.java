package com.cineverse.erpc.access.dto;

import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.employee.aggregate.Employee;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestAccessRequestDTO {
    private List<AccessRight> accessRight;
    private Employee employee;
}
