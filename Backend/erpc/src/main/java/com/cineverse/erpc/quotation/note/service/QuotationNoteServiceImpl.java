package com.cineverse.erpc.quotation.note.service;

import com.cineverse.erpc.quotation.note.aggregate.QuotationNote;
import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseDeleteQuotationNote;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.repo.QuotationNoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotationNoteServiceImpl implements QuotationNoteService{
    private ModelMapper mapper;
    private QuotationNoteRepository quotationNoteRepository;

    @Autowired
    public QuotationNoteServiceImpl(ModelMapper mapper, QuotationNoteRepository quotationNoteRepository) {
        this.mapper = mapper;
        this.quotationNoteRepository = quotationNoteRepository;
    }

    @Override
    public ResponseRegistQuotationNoteDTO registQuotationNote(RequestRegistQuotationNoteDTO requestQuotationNote) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuotationNote quotationNote = mapper.map(requestQuotationNote, QuotationNote.class);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(date);
        quotationNote.setQuotationNoteDate(currentDate);

        quotationNoteRepository.save(quotationNote);

        return mapper.map(quotationNote, ResponseRegistQuotationNoteDTO.class);
    }

    @Override
    public List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(long quotationId) {
        List<QuotationNote> quotationNotes = quotationNoteRepository.findByQuotationQuotationId(quotationId);

        return quotationNotes.stream().map(quotationNote -> mapper
                .map(quotationNote, ResponseFindAllQuotationNotesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDeleteQuotationNote deleteQuotationNote(long quotationNoteId) {
       QuotationNote quotationNote = quotationNoteRepository.findById(quotationNoteId)
               .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 견적서 참고사항 입니다."));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(date);
       quotationNote.setQuotationDeleteDate(currentDate);

       quotationNoteRepository.save(quotationNote);

       return mapper.map(quotationNote, ResponseDeleteQuotationNote.class);
    }
}
