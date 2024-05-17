package com.cineverse.erpc.slip.taxinvoice.service;

import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxInvoiceServiceImpl implements TaxInvoiceService {

    private final ModelMapper modelMapper;
    private final TaxInvoiceRepository taxInvoiceRepository;

    @Autowired
    public TaxInvoiceServiceImpl(ModelMapper modelMapper, TaxInvoiceRepository taxInvoiceRepository) {
        this.modelMapper = modelMapper;
        this.taxInvoiceRepository = taxInvoiceRepository;
    }

    @Override
    @Transactional
    public TaxInvoiceRequest registTaxInvoiceRequest(TaxInvoiceRequestDTO taxInvoiceRequestDTO) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        taxInvoiceRequestDTO.setTaxInvoiceRequestDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TaxInvoiceRequest newTaxInvoiceRequest = modelMapper.map(taxInvoiceRequestDTO, TaxInvoiceRequest.class);
        newTaxInvoiceRequest = taxInvoiceRepository.save(newTaxInvoiceRequest);

        return newTaxInvoiceRequest;
    }

    @Override
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        List<TaxInvoiceRequest> taxInvoiceRequestList = taxInvoiceRepository.findAll();

        return taxInvoiceRequestList.stream().map(taxInvoiceRequest -> modelMapper
                        .map(taxInvoiceRequest, TaxInvoiceRequest.class))
                        .collect(Collectors.toList());
    }

    @Override
    public TaxInvoiceRequestDTO findTaxInvoiceById(Long taxInvoiceRequestId) {
        TaxInvoiceRequest taxInvoiceRequest = taxInvoiceRepository.findById(taxInvoiceRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 요청입니다."));

        taxInvoiceRepository.save(taxInvoiceRequest);
        TaxInvoiceRequestDTO taxInvoiceRequestDTO = modelMapper.map(taxInvoiceRequest, TaxInvoiceRequestDTO.class);

        return taxInvoiceRequestDTO;
    }
}
