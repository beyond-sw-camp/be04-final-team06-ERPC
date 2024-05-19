package com.cineverse.erpc.approval.service;

import com.cineverse.erpc.approval.repo.ContractApprovalRepository;
import com.cineverse.erpc.approval.repo.QuotationApprovalRepository;
import com.cineverse.erpc.approval.repo.ShipmentApprovalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService{
    private ModelMapper mapper;
    private ContractApprovalRepository contractApprovalRepository;
    private QuotationApprovalRepository quotationApprovalRepository;
    private ShipmentApprovalRepository shipmentApprovalRepository;

    @Autowired
    public ApprovalServiceImpl(ModelMapper mapper,
                               ContractApprovalRepository contractApprovalRepository,
                               QuotationApprovalRepository quotationApprovalRepository,
                               ShipmentApprovalRepository shipmentApprovalRepository) {
        this.mapper = mapper;
        this.contractApprovalRepository = contractApprovalRepository;
        this.quotationApprovalRepository = quotationApprovalRepository;
        this.shipmentApprovalRepository = shipmentApprovalRepository;
    }
}
