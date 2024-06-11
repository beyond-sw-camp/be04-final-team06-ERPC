package com.cineverse.erpc.contract.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.contract.aggregate.ContractCategory;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.ContractFile;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractDTO {
    private long contractId;
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
    private Transaction transaction;
    private Account account;
    private Warehouse warehouse;
    private ContractCategory contractCategory;
    private List<ContractProductDTO> contractProduct;
    private List<ContractFile> contractFile;
}
