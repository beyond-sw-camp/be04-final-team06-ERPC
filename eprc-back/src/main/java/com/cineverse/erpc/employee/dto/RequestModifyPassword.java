package com.cineverse.erpc.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestModifyPassword {
    private long employeeId;
    private String employeePassword;
}
