package com.cineverse.erpc.slip.taxinvoiceissue.service;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestRepository;
import com.cineverse.erpc.slip.taxinvoiceissue.aggregate.TaxInvoiceIssue;
import com.cineverse.erpc.slip.taxinvoiceissue.dto.TaxInvoiceIssueDTO;
import com.cineverse.erpc.slip.taxinvoiceissue.repository.TaxInvoiceIssueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaxInvoiceIssueService {

    private final ModelMapper modelMapper;
    private final TaxInvoiceIssueRepository taxInvoiceIssueRepository;
    private final TaxInvoiceRequestRepository taxInvoiceRequestRepository;

    @Autowired
    public TaxInvoiceIssueService(ModelMapper modelMapper,
                                  TaxInvoiceIssueRepository taxInvoiceIssueRepository,
                                  TaxInvoiceRequestRepository taxInvoiceRequestRepository) {
        this.modelMapper = modelMapper;
        this.taxInvoiceIssueRepository = taxInvoiceIssueRepository;
        this.taxInvoiceRequestRepository = taxInvoiceRequestRepository;
    }

    public TaxInvoiceIssueDTO modifyIssue(TaxInvoiceIssue taxInvoiceIssue) {
        TaxInvoiceIssue invoiceIssue = taxInvoiceIssueRepository.findById(taxInvoiceIssue.getTaxInvoiceIssueId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 세금계산서 처리 요청입니다."));

        TaxInvoiceRequest taxInvoiceRequest =
                taxInvoiceRequestRepository.findByTaxInvoiceRequestId(invoiceIssue.getTaxInvoiceRequest());

        if (taxInvoiceIssue.getTaxInvoiceRejectReason() != null) {
            invoiceIssue.setTaxInvoiceRejectReason(taxInvoiceIssue.getTaxInvoiceRejectReason());
        }

        if (taxInvoiceIssue.getTaxInvoiceRequestStatus() != null) {
            invoiceIssue.setTaxInvoiceRequestStatus(taxInvoiceIssue.getTaxInvoiceRequestStatus());
            taxInvoiceRequest.setTaxInvoiceRequestStatus(taxInvoiceIssue.getTaxInvoiceRequestStatus());
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String issueDate = format.format(date);

        taxInvoiceIssue.setTaxInvoiceIssueDate(issueDate);


        taxInvoiceIssueRepository.save(taxInvoiceIssue);
        taxInvoiceRequestRepository.save(taxInvoiceRequest);

        return modelMapper.map(taxInvoiceIssue, TaxInvoiceIssueDTO.class);
    }
}
