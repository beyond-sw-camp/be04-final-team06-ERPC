package com.cineverse.erpc.slip.taxinvoice.service;

import com.cineverse.erpc.file.service.FileUploadService;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceProcess;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequestStatus;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceProcessDTO;
import com.cineverse.erpc.slip.taxinvoice.dto.TaxInvoiceRequestDTO;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceProcessRepository;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestRepository;
import com.cineverse.erpc.slip.taxinvoice.repository.TaxInvoiceRequestStatusRepository;
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
    private final TaxInvoiceProcessRepository taxInvoiceProcessRepository;
    private final TaxInvoiceRequestStatusRepository taxInvoiceRequestStatusRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public TaxInvoiceServiceImpl(ModelMapper modelMapper,
                                 TaxInvoiceRequestRepository taxInvoiceRequestRepository,
                                 TaxInvoiceProcessRepository taxInvoiceProcessRepository,
                                 TaxInvoiceRequestStatusRepository taxInvoiceRequestStatusRepository, FileUploadService fileUploadService) {
        this.modelMapper = modelMapper;
        this.taxInvoiceRequestRepository = taxInvoiceRequestRepository;
        this.taxInvoiceProcessRepository = taxInvoiceProcessRepository;
        this.taxInvoiceRequestStatusRepository = taxInvoiceRequestStatusRepository;
        this.fileUploadService = fileUploadService;
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
        newTaxInvoiceRequest = taxInvoiceRequestRepository.save(newTaxInvoiceRequest);

        TaxInvoiceProcess newTaxInvoiceProcess = new TaxInvoiceProcess();
        newTaxInvoiceProcess.setTaxInvoiceRequest(newTaxInvoiceRequest);

        TaxInvoiceRequestStatus defaultStatus = taxInvoiceRequestStatusRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 세금처리 상태입니다."));
        newTaxInvoiceProcess.setTaxInvoiceRequestStatus(defaultStatus);

        taxInvoiceProcessRepository.save(newTaxInvoiceProcess);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileUploadService.saveTaxInvoiceFile(file, newTaxInvoiceRequest);
            }
        }

        return newTaxInvoiceRequest;
    }

    @Override
    public List<TaxInvoiceRequest> findTaxInvoiceList() {
        List<TaxInvoiceRequest> taxInvoiceRequestList = taxInvoiceRequestRepository.findAll();

        return taxInvoiceRequestList.stream().map(taxInvoiceRequest -> modelMapper
                        .map(taxInvoiceRequest, TaxInvoiceRequest.class))
                        .collect(Collectors.toList());
    }

    @Override
    public TaxInvoiceRequestDTO findTaxInvoiceById(Long taxInvoiceRequestId) {
        TaxInvoiceRequest taxInvoiceRequest = taxInvoiceRequestRepository.findById(taxInvoiceRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 요청입니다."));

        taxInvoiceRequestRepository.save(taxInvoiceRequest);
        TaxInvoiceRequestDTO taxInvoiceRequestDTO = modelMapper.map(taxInvoiceRequest, TaxInvoiceRequestDTO.class);

        return taxInvoiceRequestDTO;
    }

    @Override
    @Transactional
    public TaxInvoiceProcess modifyProcess(Long taxInvoiceProcessId, TaxInvoiceProcessDTO processDTO) {

        TaxInvoiceProcess process = taxInvoiceProcessRepository.findById(taxInvoiceProcessId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 세금계약서 요청입니다."));

        process.setTaxInvoiceProcessContent(processDTO.getTaxInvoiceProcessContent());

        if (processDTO.getTaxInvoiceProcessDate() != null && !processDTO.getTaxInvoiceProcessDate().isEmpty()) {
            process.setTaxInvoiceProcessDate(processDTO.getTaxInvoiceProcessDate());
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            process.setTaxInvoiceProcessDate(dateFormat.format(new Date()));
        }

        if (processDTO.getTaxInvoiceRequestStatus() != null) {
            process.setTaxInvoiceRequestStatus(processDTO.getTaxInvoiceRequestStatus());
        }

        return taxInvoiceProcessRepository.save(process);
    }

    @Override
    public List<TaxInvoiceProcess> findProcessList() {
        List<TaxInvoiceProcess> processList = taxInvoiceProcessRepository.findAll();

        return processList.stream().map(process -> modelMapper
                        .map(process, TaxInvoiceProcess.class))
                        .collect(Collectors.toList());
    }
}
