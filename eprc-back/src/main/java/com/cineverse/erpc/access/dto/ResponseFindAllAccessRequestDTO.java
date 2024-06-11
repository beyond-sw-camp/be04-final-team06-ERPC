package com.cineverse.erpc.access.dto;

import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.employee.aggregate.Employee;
import lombok.Data;

@Data
public class ResponseFindAllAccessRequestDTO {
    private long accessRequestId;
    private String accessRequestDate;
    private String accessRequestStatus;
    private AccessRight accessRight;
    private Employee employee;
}
