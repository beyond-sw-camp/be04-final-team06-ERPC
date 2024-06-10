package com.cineverse.erpc.approval.dto.quotation;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Data;

@Data
public class RequestRegistQuotationApproval {

    private String approvalContent;
    private Quotation quotation;
}
