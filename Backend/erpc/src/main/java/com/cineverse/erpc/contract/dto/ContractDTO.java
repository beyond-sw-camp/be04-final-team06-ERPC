package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.warehouse.aggregate.entity.Warehouse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractDTO {
    private Long contractId;
    private String contractDate;
    private String contractDeleteDate;
    private String contractNote;
    private Long contractTotalPrice;
    private String contractDueDate;
    private Long downPayment;
    private Long progressPayment;
    private Long balance;
    private String contractCode;
    private Employee employee;
    private Account account;
    private Warehouse warehouse;
    private ContractCategory contractCategory;
}
