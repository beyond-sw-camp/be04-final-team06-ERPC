package com.cineverse.erpc.approval.dto.contract;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegistContractApproval {

    private String approvalContent;
    private Contract contract;
}
