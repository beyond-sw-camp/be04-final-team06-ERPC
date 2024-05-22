package com.cineverse.erpc.smtp.dto;

import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import lombok.Data;

@Data
public class RequestQuotationApprovalMail {
    QuotationApproval quotationApproval;
}
