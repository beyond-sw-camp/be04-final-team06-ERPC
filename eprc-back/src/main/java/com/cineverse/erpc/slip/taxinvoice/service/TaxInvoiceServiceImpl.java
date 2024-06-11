package com.cineverse.erpc.slip.taxinvoice.service;

import com.cineverse.erpc.file.service.FileUploadService;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.order.order.repo.OrderRepository;
import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.repository.CollectionRepository;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestRepository;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestStatusRepository;
import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import com.cineverse.erpc.slip.taxinvoiceissue.repository.TaxInvoiceIssueRepository;
import com.cineverse.erpc.slip.taxinvoiceissue.service.TaxInvoiceIssueService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxInvoiceServiceImpl implements TaxInvoiceService {

    private final ModelMapper modelMapper;
    private final TaxInvoiceRequestRepository taxInvoiceRequestRepository;
    private final TaxInvoiceRequestStatusRepository taxInvoiceRequestStatusRepository;
    private final FileUploadService fileUploadService;
    private final TaxInvoiceIssueRepository taxInvoiceIssueRepository;
    private final OrderRepository orderRepository;
    private final CollectionRepository collectionRepository;

    @Autowired
    public TaxInvoiceServiceImpl(ModelMapper modelMapper,
                                 TaxInvoiceRequestRepository taxInvoiceRequestRepository,
                                 TaxInvoiceRequestStatusRepository taxInvoiceRequestStatusRepository,
                                 FileUploadService fileUploadService,
                                 TaxInvoiceIssueRepository taxInvoiceIssueRepository,
                                 OrderRepository orderRepository,
                                 CollectionRepository collectionRepository) {
        this.modelMapper = modelMapper;
        this.taxInvoiceRequestRepository = taxInvoiceRequestRepository;
        this.taxInvoiceRequestStatusRepository = taxInvoiceRequestStatusRepository;
        this.fileUploadService = fileUploadService;
        this.taxInvoiceIssueRepository = taxInvoiceIssueRepository;
        this.orderRepository = orderRepository;
        this.collectionRepository = collectionRepository;
    }

    @Override
    @Transactional
    public TaxInvoiceRequest registTaxInvoiceRequest(TaxInvoiceRequestDTO taxInvoiceRequestDTO,
                                                     MultipartFile[] files) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        taxInvoiceRequestDTO.setTaxInvoiceRequestDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TaxInvoiceRequest newTaxInvoiceRequest = modelMapper.map(taxInvoiceRequestDTO, TaxInvoiceRequest.class);

        TaxInvoiceRequestStatus defaultStatus = taxInvoiceRequestStatusRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 세금처리 상태입니다."));

        newTaxInvoiceRequest.setTaxInvoiceRequestStatus(defaultStatus);
        newTaxInvoiceRequest = taxInvoiceRequestRepository.save(newTaxInvoiceRequest);

        //
        Order order = orderRepository.findById(taxInvoiceRequestDTO.getOrder().getOrderRegistrationId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 수주입니다."));

        Collection collection = collectionRepository.findById(taxInvoiceRequestDTO.getCollection().getCollectionId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 입금내역입니다."));

        order.setTotalBalance(order.getTotalBalance()-collection.getDepositPrice());

        if (collection.getDepositCategory().getDepositCategoryId() == 3 ||
                collection.getDepositCategory().getDepositCategoryId() == 4) {
            order.setDepositDate(collection.getDepositDate());
        }

        orderRepository.save(order);

        TaxInvoiceIssue newTaxInvoiceIssue = new TaxInvoiceIssue();
        newTaxInvoiceIssue.setTaxInvoiceRequest(newTaxInvoiceRequest.getTaxInvoiceRequestId());

        newTaxInvoiceIssue.setTaxInvoiceRequestStatus(defaultStatus);

        taxInvoiceIssueRepository.save(newTaxInvoiceIssue);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileUploadService.saveTaxInvoiceFile(file, newTaxInvoiceRequest);
            }
        }

        return newTaxInvoiceRequest;
    }

    @Override
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        List<TaxInvoiceRequest> taxInvoiceRequestList
                = taxInvoiceRequestRepository.findAllByOrderByTaxInvoiceRequestIdDesc();

        return taxInvoiceRequestList.stream().map(taxInvoiceRequest -> modelMapper
                        .map(taxInvoiceRequest, TaxInvoiceRequest.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaxInvoiceRequestDTO findTaxInvoiceById(long taxInvoiceRequestId) {
        TaxInvoiceRequest taxInvoiceRequest = taxInvoiceRequestRepository.findById(taxInvoiceRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 요청입니다."));

        taxInvoiceRequestRepository.save(taxInvoiceRequest);
        TaxInvoiceRequestDTO taxInvoiceRequestDTO = modelMapper.map(taxInvoiceRequest, TaxInvoiceRequestDTO.class);

        return taxInvoiceRequestDTO;
    }
}
