package com.cineverse.erpc.salesopp.opportunity.service;

import com.cineverse.erpc.salesopp.opportunity.aggregate.entity.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
import com.cineverse.erpc.salesopp.opportunity.repository.SalesOppRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public SalesOpp registSalesOpp(SalesOppDTO oppDTO) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);

        oppDTO.setOppDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SalesOpp newOpp = modelMapper.map(oppDTO, SalesOpp.class);
        newOpp = salesOppRepository.save(newOpp);

        return newOpp;
    }

    @Override
    @Transactional
    public SalesOpp modifySalesOpp(Long salesOppId, SalesOppDTO opp) throws UsernameNotFoundException {

        Optional<SalesOpp> optionalSalesOpp = salesOppRepository.findById(salesOppId);

        if (optionalSalesOpp.isEmpty()){
            throw new UsernameNotFoundException("존재하지 않는 영업기회입니다.");
        }

        SalesOpp salesOpp = optionalSalesOpp.get();

        if (opp.getOppAccountName() != null) {
            salesOpp.setOppAccountName(opp.getOppAccountName());
        }

        if (opp.getOppAccountPic() != null) {
            salesOpp.setOppAccountPic(opp.getOppAccountPic());
        }

        if (opp.getOppAccountContact() != null) {
            salesOpp.setOppAccountContact(opp.getOppAccountContact());
        }

        if (opp.getOppAccountLocation() != null) {
            salesOpp.setOppAccountLocation(opp.getOppAccountLocation());
        }

        if (opp.getOppAccountEmail() != null) {
            salesOpp.setOppAccountEmail(opp.getOppAccountEmail());
        }

        if (opp.getOppAccountNote() != null) {
            salesOpp.setOppAccountNote(opp.getOppAccountNote());
        }

        return salesOppRepository.save(salesOpp);
    }

    @Override
    @Transactional
    public SalesOpp changeSalesOppStatus(Long salesOppId, SalesOppDTO opp) {

        Optional<SalesOpp> optionalSalesOpp = salesOppRepository.findById(salesOppId);

        if (optionalSalesOpp.isEmpty()){
            throw new UsernameNotFoundException("존재하지 않는 영업기회입니다.");
        }

        SalesOpp salesOpp = optionalSalesOpp.get();

        if (opp.getSalesOppStatus() != null) {
            salesOpp.setSalesOppStatus(opp.getSalesOppStatus());
        }

        return salesOppRepository.save(salesOpp);
    }

    @Override
    public List<SalesOpp> findSalesOppList() {
        List<SalesOpp> salesOppList = salesOppRepository.findByOppDeleteDateIsNullOrderBySalesOppIdDesc();

        return salesOppList.stream().map(salesOpp -> modelMapper
                            .map(salesOpp, SalesOpp.class))
                            .collect(Collectors.toList());
    }

    @Override
    public SalesOppDTO findSalesOppById(Long salesOppId) {

        SalesOpp salesOpp = salesOppRepository.findById(salesOppId)
                .orElseThrow(EntityNotFoundException::new);

        salesOppRepository.save(salesOpp);
        SalesOppDTO salesOppDTO = modelMapper.map(salesOpp, SalesOppDTO.class);

        return salesOppDTO;
    }
}