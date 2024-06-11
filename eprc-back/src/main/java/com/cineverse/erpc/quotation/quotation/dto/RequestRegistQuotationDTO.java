package com.cineverse.erpc.quotation.quotation.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.QuotationFile;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.warehouse.aggregate.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestRegistQuotationDTO {
    private String quotationNote;
    private long quotationTotalCost;
    private String quotationDueDate;
    private Employee employee;
    private Account account;
    private Warehouse warehouse;
    private List<QuotationProduct> quotationProduct;
    private List<QuotationFile> quotationFile;
}
