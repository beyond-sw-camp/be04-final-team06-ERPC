package com.cineverse.erpc.salesopp.opportunity.dto;

import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.salesopp.opportunity.aggregate.vo.SalesOppStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SalesOppDTO {
    private long salesOppId;
    private String oppAccountName;
    private String oppAccountPic;
    private String oppAccountContact;
    private String oppAccountLocation;
    private String oppAccountEmail;
    private String oppAccountNote;
    private Employee employee;
    private SalesOppStatus salesOppStatus;
}
