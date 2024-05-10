package com.cineverse.erpc.salesopp.opportunity.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOppServiceImpl implements SalesOppService {

    private final ModelMapper modelMapper;
    private final SalesOppRepository salesOppRepository;

    @Autowired

    public SalesOppServiceImpl(ModelMapper modelMapper, SalesOppRepository salesOppRepository) {
        this.modelMapper = modelMapper;
        this.salesOppRepository = salesOppRepository;
    }

    @Override
    public SalesOpp registSalesOpp(SalesOppDTO newSalesOpp) {
        return null;
    }

    @Override
    public SalesOppDTO modifySalesOpp(Long salesOppId, SalesOppDTO salesOpp) {
        return null;
    }

    @Override
    public List<SalesOpp> findSalesOppList() {
        return List.of();
    }

    @Override
    public SalesOppDTO findSalesOppById(Long salesOppId) {
        return null;
    }
}
