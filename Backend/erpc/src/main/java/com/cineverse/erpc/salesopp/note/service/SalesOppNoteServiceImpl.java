package com.cineverse.erpc.salesopp.note.service;

import com.cineverse.erpc.salesopp.note.aggregate.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.note.repository.SalesOppNoteRepository;
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
public class SalesOppNoteServiceImpl implements SalesOppNoteService {

    private final ModelMapper modelMapper;
    private final SalesOppNoteRepository salesOppNoteRepository;

    @Autowired

    public SalesOppNoteServiceImpl(ModelMapper modelMapper, SalesOppNoteRepository salesOppNoteRepository) {
        this.modelMapper = modelMapper;
        this.salesOppNoteRepository = salesOppNoteRepository;
    }

    @Override
    @Transactional
    public SalesOppNote registSalesOppNote(SalesOppNoteDTO oppNote) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);

        oppNote.setSalesOppNoteDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SalesOppNote newOppNote = modelMapper.map(oppNote, SalesOppNote.class);
        newOppNote = salesOppNoteRepository.save(newOppNote);

        return newOppNote;
    }

    @Override
    @Transactional
    public SalesOppNote modifySalesOppNote(long salesOppNoteId, SalesOppNoteDTO oppNote)
            throws UsernameNotFoundException {

        Optional<SalesOppNote> optionalSalesOppNote = salesOppNoteRepository.findById(salesOppNoteId);

        if (optionalSalesOppNote.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 영업기회 참고사항입니다.");
        }

        SalesOppNote salesOppNote = optionalSalesOppNote.get();

        if (oppNote.getSalesOppNote() != null) {
            salesOppNote.setSalesOppNote(oppNote.getSalesOppNote());
        }

        return salesOppNoteRepository.save(salesOppNote);
    }

    @Override
    public SalesOppNote deleteSalesOppNote(long salesOppNoteId) throws UsernameNotFoundException {
        Optional<SalesOppNote> optionalSalesOppNote = salesOppNoteRepository.findById(salesOppNoteId);

        if (optionalSalesOppNote.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 영업기회 참고사항입니다.");
        }

        SalesOppNote oppNote = optionalSalesOppNote.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        oppNote.setSalesOppNoteDeleteDate(deleteDate);

        return salesOppNoteRepository.save(oppNote);
    }

    @Override
    public List<SalesOppNote> findsalesOppNoteList() {
        List<SalesOppNote> salesOppNoteList =
                salesOppNoteRepository.findBySalesOppNoteDeleteDateIsNullOrderBySalesOppNoteIdDesc();

        return salesOppNoteList.stream().map(salesOppNote -> modelMapper
                        .map(salesOppNote, SalesOppNote.class))
                        .collect(Collectors.toList());
    }
}
