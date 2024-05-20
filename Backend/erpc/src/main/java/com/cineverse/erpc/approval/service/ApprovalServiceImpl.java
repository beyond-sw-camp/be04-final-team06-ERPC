package com.cineverse.erpc.approval.service;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import com.cineverse.erpc.approval.dto.contract.RequestRegistContractApproval;
import com.cineverse.erpc.approval.dto.contract.ResponseContractApprovalList;
import com.cineverse.erpc.approval.dto.contract.ResponseFindContractApproval;
import com.cineverse.erpc.approval.dto.contract.ResponseRegistContractApproval;
import com.cineverse.erpc.approval.dto.quotation.RequestRegistQuotationApproval;
import com.cineverse.erpc.approval.dto.quotation.ResponseFindQuotationApproval;
import com.cineverse.erpc.approval.dto.quotation.ResponseQuotationApprovalList;
import com.cineverse.erpc.approval.dto.quotation.ResponseRegistQuotationApproval;
import com.cineverse.erpc.approval.dto.shipment.RequestRegistShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseFindShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseRegistShipmentApproval;
import com.cineverse.erpc.approval.dto.shipment.ResponseShipmentApprovalList;
import com.cineverse.erpc.approval.repo.ContractApprovalRepository;
import com.cineverse.erpc.approval.repo.QuotationApprovalRepository;
import com.cineverse.erpc.approval.repo.ShipmentApprovalRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public ResponseRegistContractApproval registContractApproval(RequestRegistContractApproval requestApproval) {

        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(1);
        approvalStatus.setApprovalStatus("결재 요청");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ContractApproval contractApproval = mapper.map(requestApproval, ContractApproval.class);

        contractApproval.setApprovalStatus(approvalStatus);
        contractApproval.setApprovalRequestDate(registDate);

        contractApprovalRepository.save(contractApproval);

        return mapper.map(contractApproval, ResponseRegistContractApproval.class);
    }

    @Override
    public ResponseRegistQuotationApproval registQuotationtApproval(RequestRegistQuotationApproval requestApproval) {

        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(1);
        approvalStatus.setApprovalStatus("결재 요청");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuotationApproval quotationApproval = mapper.map(requestApproval, QuotationApproval.class);

        quotationApproval.setApprovalStatus(approvalStatus);
        quotationApproval.setApprovalRequestDate(registDate);

        quotationApprovalRepository.save(quotationApproval);

        return mapper.map(quotationApproval, ResponseRegistQuotationApproval.class);
    }

    @Override
    public ResponseRegistShipmentApproval registShipmentApproval(RequestRegistShipmentApproval requestApproval) {

        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalStatusId(1);
        approvalStatus.setApprovalStatus("결재 요청");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ShipmentApproval shipmentApproval = mapper.map(requestApproval, ShipmentApproval.class);

        shipmentApproval.setApprovalStatus(approvalStatus);
        shipmentApproval.setApprovalRequestDate(registDate);

        shipmentApprovalRepository.save(shipmentApproval);

        return mapper.map(shipmentApproval, ResponseRegistShipmentApproval.class);
    }

    @Override
    public List<ResponseContractApprovalList> findContractApprovalList() {
        List<ContractApproval> contractApprovals = contractApprovalRepository.findAll();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return contractApprovals.stream().map(contractApproval -> mapper
                .map(contractApproval, ResponseContractApprovalList.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseQuotationApprovalList> findQuotationApprovalList() {
        List<QuotationApproval> quotationApprovals = quotationApprovalRepository.findAll();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return quotationApprovals.stream().map(quotationApproval -> mapper
                        .map(quotationApproval, ResponseQuotationApprovalList.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseShipmentApprovalList> findShipmentApprovalList() {
        List<ShipmentApproval> shipmentApprovals = shipmentApprovalRepository.findAll();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return shipmentApprovals.stream().map(shipmentApproval -> mapper
                        .map(shipmentApproval, ResponseShipmentApprovalList.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindContractApproval findContractApproval(long contractId) {
        ContractApproval contractApproval = contractApprovalRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        return mapper.map(contractApproval, )
    }

    @Override
    public ResponseFindQuotationApproval findQuotationApproval(long quotationId) {
        return null;
    }

    @Override
    public ResponseFindShipmentApproval findShipmentApproval(long contractId) {
        return null;
    }
}
