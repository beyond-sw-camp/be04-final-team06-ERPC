package com.cineverse.erpc.admin.delete.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppDeleteRequestRepository;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
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

    @Override
    @Transactional
    public SalesOppDeleteRequest changeRequestStatus(int salesOppDeleteRequestId, SalesOppDeleteRequestDTO deleteOppDTO) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = dateFormat.format(new Date());

        SalesOppDeleteRequest deleteReqOpp = salesOppDeleteRequestRepository.findById(salesOppDeleteRequestId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영업기회 삭제요청입니다: " + salesOppDeleteRequestId));

        SalesOpp salesOpp = deleteReqOpp.getSalesOpp();

        if (salesOpp == null) {
            throw new IllegalStateException("해당 삭제 요청 ID에 연결된 영업기회가 존재하지 않습니다: " + salesOppDeleteRequestId);
        }

        salesOpp.setOppDeleteDate(deleteDate);
        salesOppRepository.save(salesOpp);

        deleteReqOpp.setRequestStatus('Y');
        salesOppDeleteRequestRepository.save(deleteReqOpp);

        return deleteReqOpp;
    }
}
