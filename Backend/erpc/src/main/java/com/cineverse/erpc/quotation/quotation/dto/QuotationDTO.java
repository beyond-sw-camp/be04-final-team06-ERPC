package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.QuotationFile;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class QuotationDTO {
    private long quotationId;
    private String quotationDate;
    private String quotationDeleteDate;
    private String quotationNote;
    private long quotationTotalCost;
    private String quotationDueDate;
    private String quotationCode;
    private Employee employee;
    private Account account;
    private Transaction transaction;
    private Warehouse warehouse;
    private List<QuotationFile> quotationFile;
}
