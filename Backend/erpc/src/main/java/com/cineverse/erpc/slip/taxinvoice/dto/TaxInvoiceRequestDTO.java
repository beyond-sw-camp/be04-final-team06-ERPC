package com.cineverse.erpc.slip.taxinvoice.dto;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.file.aggregate.TaxInvoiceFile;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TaxInvoiceRequestDTO {
    private long taxInvoiceRequestId;
    private String taxInvoiceRequestDate;
    private String taxInvoiceNote;
    private Order order;
    private Account account;
    private Employee employee;
    private Collection collection;
    private TaxInvoiceRequestStatus taxInvoiceRequestStatus;
    private List<TaxInvoiceFile> taxInvoiceFile;
}
