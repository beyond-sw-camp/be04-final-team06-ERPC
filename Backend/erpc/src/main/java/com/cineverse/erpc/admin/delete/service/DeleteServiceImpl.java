package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.account.account.aggregate.Account;
import com.cineverse.erpc.account.account.aggregate.AccountDeleteRequest;
import com.cineverse.erpc.account.account.repository.AccountDeleteRequestRepository;
import com.cineverse.erpc.account.account.repository.AccountRepository;
import com.cineverse.erpc.admin.delete.dto.account.RequestAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.account.ResponseAccountDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.account.ResponseFindAccoundDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.RequestOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.order.ResponseFindOrderDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.order.ResponseOrderDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.RequestQuotationDeleteRequestProcess;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseFindQuotationDeleteRequest;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestList;
import com.cineverse.erpc.admin.delete.dto.quotation.ResponseQuotationDeleteRequestProcess;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.repository.ContractDeleteRequestRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.aggregate.OrderDeleteRequest;
import com.cineverse.erpc.order.order.dto.ResponseDeleteOrder;
import com.cineverse.erpc.order.order.repo.OrderDeleteRequestRepository;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationDeleteRequest;
import com.cineverse.erpc.quotation.quotation.repo.QuotationDeleteRequestRepository;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppDeleteRequestRepository;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteServiceImpl implements DeleteService {

    private final ModelMapper modelMapper;
    private final SalesOppRepository salesOppRepository;
    private final SalesOppDeleteRequestRepository salesOppDeleteRequestRepository;
    private final ContractRepository contractRepository;
    private final ContractDeleteRequestRepository contractDeleteRequestRepository;
    private final QuotationDeleteRequestRepository quotationDeleteRequestRepository;
    private final QuotationRepository quotationRepository;
    private final AccountDeleteRequestRepository accountDeleteRequestRepository;
    private final AccountRepository accountRepository;
    private final OrderDeleteRequestRepository orderDeleteRequestRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public DeleteServiceImpl(ModelMapper modelMapper,
                             SalesOppRepository salesOppRepository,
                             SalesOppDeleteRequestRepository salesOppDeleteRequestRepository,
                             ContractRepository contractRepository,
                             ContractDeleteRequestRepository contractDeleteRequestRepository,
                             QuotationDeleteRequestRepository quotationDeleteRequestRepository,
                             QuotationRepository quotationRepository,
                             AccountDeleteRequestRepository accountDeleteRequestRepository,
                             AccountRepository accountRepository,
                             OrderDeleteRequestRepository orderDeleteRequestRepository,
                             OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.salesOppRepository = salesOppRepository;
        this.salesOppDeleteRequestRepository = salesOppDeleteRequestRepository;
        this.contractRepository = contractRepository;
        this.contractDeleteRequestRepository = contractDeleteRequestRepository;
        this.quotationDeleteRequestRepository = quotationDeleteRequestRepository;
        this.quotationRepository = quotationRepository;
        this.accountDeleteRequestRepository = accountDeleteRequestRepository;
        this.accountRepository = accountRepository;
        this.orderDeleteRequestRepository = orderDeleteRequestRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<SalesOppDeleteRequest> findSalesOppDeleteRequestList() {
        List<SalesOppDeleteRequest> oppDeleteRequestList = salesOppDeleteRequestRepository.findAll();

        return oppDeleteRequestList.stream().map(salesOppDeleteRequest -> modelMapper
                        .map(salesOppDeleteRequest, SalesOppDeleteRequest.class))
                .collect(Collectors.toList());
    }

    @Override
    public SalesOppDeleteRequestDTO findSalesOppDeleteRequestById(long salesOppDeleteRequestId) {
        SalesOppDeleteRequest oppDeleteRequest = salesOppDeleteRequestRepository.findById(salesOppDeleteRequestId)
                .orElseThrow(EntityNotFoundException::new);

        salesOppDeleteRequestRepository.save(oppDeleteRequest);
        SalesOppDeleteRequestDTO oppDeleteRequestDTO =
                modelMapper.map(oppDeleteRequest, SalesOppDeleteRequestDTO.class);

        return oppDeleteRequestDTO;
    }

    @Override
    @Transactional
    public SalesOppDeleteRequest changeOppDeleteRequestStatus(long salesOppDeleteRequestId,
                                                              SalesOppDeleteRequestDTO deleteOppDTO) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = dateFormat.format(new Date());

        SalesOppDeleteRequest deleteReqOpp = salesOppDeleteRequestRepository.findById(salesOppDeleteRequestId)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 영업기회 삭제요청입니다: " + salesOppDeleteRequestId));

        SalesOpp salesOpp = deleteReqOpp.getSalesOpp();

        if (salesOpp == null) {
            throw new IllegalStateException("해당 삭제 요청 ID에 연결된 영업기회가 존재하지 않습니다: " + salesOppDeleteRequestId);
        }

        salesOpp.setOppDeleteDate(deleteDate);
        salesOppRepository.save(salesOpp);

        deleteReqOpp.setSalesOppDeleteRequestStatus('Y');
        salesOppDeleteRequestRepository.save(deleteReqOpp);

        return deleteReqOpp;
    }

    @Override
    public List<ContractDeleteRequest> findContractDeleteRequestList() {
        List<ContractDeleteRequest> contractDeleteRequestList = contractDeleteRequestRepository.findAll();

        return contractDeleteRequestList.stream().map(contractDeleteRequest -> modelMapper
                        .map(contractDeleteRequest, ContractDeleteRequest.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContractDeleteRequestDTO findContractDeleteRequestById(long contractDeleteRequestId) {
        ContractDeleteRequest contractDeleteRequest = contractDeleteRequestRepository.findById(contractDeleteRequestId)
                .orElseThrow(EntityNotFoundException::new);

        contractDeleteRequestRepository.save(contractDeleteRequest);
        ContractDeleteRequestDTO contractDeleteRequestDTO =
                modelMapper.map(contractDeleteRequest, ContractDeleteRequestDTO.class);

        return contractDeleteRequestDTO;
    }

    @Override
    public ContractDeleteRequest changeContractDeleteRequestStatus(ContractDeleteRequestDTO deleteContractDTO,
                                                                   long contractDeleteRequestId) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = dateFormat.format(new Date());

        ContractDeleteRequest deleteReqContract = contractDeleteRequestRepository.findById(contractDeleteRequestId)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 계약서 삭제요청입니다: " + contractDeleteRequestId));

        Contract contract = deleteReqContract.getContract();


        if (contract == null) {
            throw new IllegalStateException("해당 삭제 요청 ID에 연결된 계약서가 존재하지 않습니다: " + contractDeleteRequestId);
        }

        contract.setContractDeleteDate(deleteDate);
        contractRepository.save(contract);

        deleteReqContract.setContractDeleteRequestStatus('Y');
        contractDeleteRequestRepository.save(deleteReqContract);

        return deleteReqContract;
    }

    @Override
    public List<ResponseQuotationDeleteRequestList> findQuotationDeleteRequestList() {
        List<QuotationDeleteRequest> quotationDeleteRequests = quotationDeleteRequestRepository.findAll();

        return quotationDeleteRequests.stream().map(quotationDeleteRequest -> modelMapper
                        .map(quotationDeleteRequest, ResponseQuotationDeleteRequestList.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindQuotationDeleteRequest findQuotationDeleteRequestById(long quotationDeleteRequestId) {
        QuotationDeleteRequest quotationDeleteRequest =
                quotationDeleteRequestRepository.findById(quotationDeleteRequestId)
                        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서 삭제 요청입니다."));

        return modelMapper.map(quotationDeleteRequest, ResponseFindQuotationDeleteRequest.class);
    }

    @Override
    public ResponseQuotationDeleteRequestProcess processQuotationDeleteRequest(
            RequestQuotationDeleteRequestProcess requestQuotationDeleteRequestProcess) {
        QuotationDeleteRequest quotationDeleteRequest =
                quotationDeleteRequestRepository
                        .findById(requestQuotationDeleteRequestProcess.getQuotationDeleteRequestId())
                        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서 삭제 요청입니다."));

        quotationDeleteRequest.setQuotationDeleteRequestStatus("Y");
        quotationDeleteRequestRepository.save(quotationDeleteRequest);

        Quotation quotation = quotationRepository
                .findById(quotationDeleteRequest.getQuotation().getQuotationId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서입니다."));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        quotation.setQuotationDeleteDate(currentDate);
        quotationRepository.save(quotation);

        quotationDeleteRequest.setQuotation(quotation);

        return modelMapper.map(quotationDeleteRequest, ResponseQuotationDeleteRequestProcess.class);
    }

    @Override
    public List<ResponseAccountDeleteRequestList> findAccountDeleteList() {
        List<AccountDeleteRequest> accountDeleteRequests = accountDeleteRequestRepository.findAll();

        return accountDeleteRequests.stream().map(accountDeleteRequest ->
                        modelMapper.map(accountDeleteRequest, ResponseAccountDeleteRequestList.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindAccoundDeleteRequest findAccountDeleteRequestById(long accountDeleteRequestId) {
        AccountDeleteRequest accountDeleteRequest = accountDeleteRequestRepository
                .findById(accountDeleteRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처 삭제 요청입니다."));

        return modelMapper.map(accountDeleteRequest, ResponseFindAccoundDeleteRequest.class);
    }

    @Override
    public ResponseAccountDeleteRequestProcess accountDeleteRequestProcess(
            RequestAccountDeleteRequestProcess requestAccountDeleteRequestProcess) {
        AccountDeleteRequest accountDeleteRequest = accountDeleteRequestRepository
                .findById(requestAccountDeleteRequestProcess.getAccountDeleteRequestId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처 삭제 요청입니다."));
        accountDeleteRequest.setAccountDeleteRequestStatus("Y");

        Account account = accountRepository.findById(accountDeleteRequest.getAccount().getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 거래처입니다."));


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        account.setAccountDeleteDate(currentDate);
        accountRepository.save(account);

        accountDeleteRequest.setAccount(account);

        return modelMapper.map(accountDeleteRequest, ResponseAccountDeleteRequestProcess.class);
    }

    @Override
    public List<ResponseOrderDeleteRequestList> findOrderDeleteRequestList() {
        List<OrderDeleteRequest> orderDeleteRequests = orderDeleteRequestRepository.findAll();

        return orderDeleteRequests.stream().map(orderDeleteRequest -> modelMapper
                        .map(orderDeleteRequest, ResponseOrderDeleteRequestList.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindOrderDeleteRequest findOrderDeleteRequestById(long orderDeleteRequestId) {
        OrderDeleteRequest orderDeleteRequest = orderDeleteRequestRepository.findById(orderDeleteRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주 삭제요청 입니다."));

        return modelMapper.map(orderDeleteRequest, ResponseFindOrderDeleteRequest.class);
    }

    @Override
    public ResponseOrderDeleteRequestProcess processOrderDeleteRequest(
            RequestOrderDeleteRequestProcess requestOrderDeleteRequestProcess) {
        OrderDeleteRequest orderDeleteRequest =
                orderDeleteRequestRepository.findById(requestOrderDeleteRequestProcess.getOrderDeleteRequestId())
                        .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주 삭제요청 입니다."));
        orderDeleteRequest.setOrderDeleteRequestStatus("Y");
        orderDeleteRequestRepository.save(orderDeleteRequest);

        Order order = orderRepository.findById(orderDeleteRequest.getOrder().getOrderRegistrationId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        order.setOrderDeleteDate(currentDate);
        orderRepository.save(order);

        orderDeleteRequest.setOrder(order);

        return modelMapper.map(orderDeleteRequest, ResponseOrderDeleteRequestProcess.class);
    }
}
