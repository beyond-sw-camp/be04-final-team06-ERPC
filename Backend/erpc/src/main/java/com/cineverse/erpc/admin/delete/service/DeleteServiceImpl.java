package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppDeleteRequestRepository;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteServiceImpl implements DeleteService {

    private final ModelMapper modelMapper;
    private final SalesOppRepository salesOppRepository;
    private final SalesOppDeleteRequestRepository salesOppDeleteRequestRepository;

    @Autowired
    public DeleteServiceImpl(ModelMapper modelMapper,
                             SalesOppRepository salesOppRepository,
                             SalesOppDeleteRequestRepository salesOppDeleteRequestRepository) {
        this.modelMapper = modelMapper;
        this.salesOppRepository = salesOppRepository;
        this.salesOppDeleteRequestRepository = salesOppDeleteRequestRepository;
    }


    @Override
    public List<SalesOppDeleteRequest> findSalesOppDeleteRequestList() {
        List<SalesOppDeleteRequest> oppDeleteRequestList = salesOppDeleteRequestRepository.findAll();

        return oppDeleteRequestList.stream().map(salesOppDeleteRequest -> modelMapper
                        .map(salesOppDeleteRequest, SalesOppDeleteRequest.class))
                        .collect(Collectors.toList());
    }

    @Override
    public SalesOppDeleteRequestDTO findSalesOppDeleteRequestById(int salesOppDeleteRequestId) {
        SalesOppDeleteRequest oppDeleteRequest = salesOppDeleteRequestRepository.findById(salesOppDeleteRequestId)
                .orElseThrow(EntityNotFoundException::new);

        salesOppDeleteRequestRepository.save(oppDeleteRequest);
        SalesOppDeleteRequestDTO oppDeleteRequestDTO = modelMapper.map(oppDeleteRequest, SalesOppDeleteRequestDTO.class);

        return oppDeleteRequestDTO;
    }
}
