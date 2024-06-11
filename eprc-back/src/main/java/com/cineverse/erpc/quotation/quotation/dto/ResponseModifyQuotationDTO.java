package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.QuotationFile;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class ResponseModifyQuotationDTO {
    private long quotationId;
    private String quotationNote;
    private long quotationTotalCost;
    private String quotationDate;
    private String quotationCode;
    private String quotationDueDate;
    private Employee employee;
    private Account account;
    private Warehouse warehouse;
    private Transaction transaction;
    private List<QuotationProduct> quotationProduct;
    private List<QuotationFile> quotationFile;
}
