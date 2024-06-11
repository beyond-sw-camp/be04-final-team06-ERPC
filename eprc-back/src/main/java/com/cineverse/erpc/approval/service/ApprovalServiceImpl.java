package com.cineverse.erpc.approval.service;

import com.cineverse.erpc.approval.aggregate.ApprovalStatus;
import com.cineverse.erpc.approval.aggregate.ContractApproval;
import com.cineverse.erpc.approval.aggregate.QuotationApproval;
import com.cineverse.erpc.approval.aggregate.ShipmentApproval;
import com.cineverse.erpc.approval.dto.contract.*;
import com.cineverse.erpc.approval.dto.quotation.*;
import com.cineverse.erpc.approval.dto.shipment.*;
import com.cineverse.erpc.approval.repo.ContractApprovalRepository;
import com.cineverse.erpc.approval.repo.QuotationApprovalRepository;
import com.cineverse.erpc.approval.repo.ShipmentApprovalRepository;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.shipment.aggregate.Shipment;
import com.cineverse.erpc.shipment.repo.ShipmentRepository;
import com.cineverse.erpc.smtp.dto.RequestContractApprovalMail;
import com.cineverse.erpc.smtp.dto.RequestQuotationApprovalMail;
import com.cineverse.erpc.smtp.dto.RequestShipmentApprovalMail;
import com.cineverse.erpc.smtp.service.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApprovalServiceImpl implements ApprovalService{
    private ModelMapper mapper;
    private ContractApprovalRepository contractApprovalRepository;
    private QuotationApprovalRepository quotationApprovalRepository;
    private ShipmentApprovalRepository shipmentApprovalRepository;
    private ShipmentRepository shipmentRepository;
    private OrderRepository orderRepository;
    private EmailService emailService;

    @Autowired
    public ApprovalServiceImpl(ModelMapper mapper,
                               ContractApprovalRepository contractApprovalRepository,
                               QuotationApprovalRepository quotationApprovalRepository,
                               ShipmentApprovalRepository shipmentApprovalRepository,
                               ShipmentRepository shipmentRepository,
                               OrderRepository orderRepository,
                               EmailService emailService) {
        this.mapper = mapper;
        this.contractApprovalRepository = contractApprovalRepository;
        this.quotationApprovalRepository = quotationApprovalRepository;
        this.shipmentApprovalRepository = shipmentApprovalRepository;
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
        this.emailService = emailService;
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

        return mapper.map(contractApproval, ResponseFindContractApproval.class);
    }

    @Override
    public ResponseFindQuotationApproval findQuotationApproval(long quotationId) {
        QuotationApproval quotationApproval = quotationApprovalRepository.findById(quotationId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        return mapper.map(quotationApproval, ResponseFindQuotationApproval.class);
    }

    @Override
    public ResponseFindShipmentApproval findShipmentApproval(long contractId) {
        ShipmentApproval shipmentApproval = shipmentApprovalRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        return mapper.map(shipmentApproval, ResponseFindShipmentApproval.class);
    }

    @Override
    public ResponseContractApprovalProcess contractApprovalProcess(RequestContractApprovalProcess requestApproval) {
        ContractApproval contractApproval = contractApprovalRepository
                .findById(requestApproval.getContractApprovalId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        contractApproval.setApprovalDate(registDate);
        contractApproval.setApprovalStatus(requestApproval.getApprovalStatus());
        contractApproval.setApprovalContent(requestApproval.getApprovalContent());

        contractApprovalRepository.save(contractApproval);

        RequestContractApprovalMail requestContractApprovalMail = new RequestContractApprovalMail();
        requestContractApprovalMail.setContractApproval(contractApproval);
        emailService.sendContractApprovalResultMail(requestContractApprovalMail);

        return mapper.map(contractApproval, ResponseContractApprovalProcess.class);
    }

    @Override
    public ResponseQuotationApprovalProcess quotationApprovalProcess(RequestQuotationApprovalProcess requestApproval) {
        QuotationApproval quotationApproval = quotationApprovalRepository
                .findById(requestApproval.getQuotationApprovalId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

         quotationApproval.setApprovalDate(registDate);
         quotationApproval.setApprovalStatus(requestApproval.getApprovalStatus());
         quotationApproval.setApprovalContent(requestApproval.getApprovalContent());

         quotationApprovalRepository.save(quotationApproval);

        RequestQuotationApprovalMail requestQuotationApprovalMail= new RequestQuotationApprovalMail();
        requestQuotationApprovalMail.setQuotationApproval(quotationApproval);
        emailService.sendQuotationApprovalResultMail(requestQuotationApprovalMail);


        return mapper.map(quotationApproval, ResponseQuotationApprovalProcess.class);
    }

    @Override
    public ResponseShipmentApprovalProcess shipmentApprovalProcess(RequestShipmentApprovalProcess requestApproval) {
        ShipmentApproval shipmentApproval = shipmentApprovalRepository
                .findById(requestApproval.getShipmentApprovalId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재입니다."));

        Order order = orderRepository.findById(shipmentApproval.getOrder().getOrderRegistrationId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주 입니다."));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        shipmentApproval.setApprovalDate(registDate);
        shipmentApproval.setApprovalStatus(requestApproval.getApprovalStatus());
        shipmentApproval.setApprovalContent(requestApproval.getApprovalContent());

        if(shipmentApproval.getApprovalStatus().getApprovalStatusId() == 2) {
            Shipment shipment = new Shipment();
            shipment.setOrderDueDate(order.getOrderDueDate());
            shipment.setTransactionCode(order.getTransaction().getTransactionCode());
            shipment.setShipmentStatus(order.getShipmentStatus());
            shipmentRepository.save(shipment);
        }

        shipmentApprovalRepository.save(shipmentApproval);

        RequestShipmentApprovalMail requestShipmentApprovalMail = new RequestShipmentApprovalMail();
        requestShipmentApprovalMail.setShipmentApproval(shipmentApproval);
        emailService.sendShipmentApprovalResultMail(requestShipmentApprovalMail);

        return mapper.map(shipmentApproval, ResponseShipmentApprovalProcess.class);
    }
}
