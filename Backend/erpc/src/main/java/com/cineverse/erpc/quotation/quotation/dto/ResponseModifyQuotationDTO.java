package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.warehouse.aggregate.entity.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class ResponseModifyQuotationDTO {
    private String quotationNote;
    private long quotationTotalCost;
    private String quotationDueDate;
    private Employee employee;
    private Account account;
    private Warehouse warehouse;
    private List<QuotationProduct> quotationProduct;
}
