package com.cineverse.erpc.salesopp.note.service;

import com.cineverse.erpc.salesopp.note.aggregate.entity.SalesOppNote;
import com.cineverse.erpc.salesopp.note.dto.SalesOppNoteDTO;
import com.cineverse.erpc.salesopp.note.repository.SalesOppNoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void registSalesOppNote(SalesOppNoteDTO newSalesOppNote) {

    }

    @Override
    public SalesOppNote modifySalesOppNote(Long salesOppNoteId, SalesOppNoteDTO salesOppNote) {
        return null;
    }

    @Override
    public SalesOppNote deleteSalesOppNote(Long salesOppNoteId) {
        return null;
    }

    @Override
    public List<SalesOppNote> findsalesOppNoteList() {
        return List.of();
    }
}
