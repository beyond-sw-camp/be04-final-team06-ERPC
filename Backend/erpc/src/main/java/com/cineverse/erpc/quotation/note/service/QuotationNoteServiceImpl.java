package com.cineverse.erpc.quotation.note.service;

import com.cineverse.erpc.account.note.aggregate.AccountNote;
import com.cineverse.erpc.quotation.note.aggregate.QuotationNote;
import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.repo.QuotationNoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registQuotationNote(RequestRegistQuotationNoteDTO requestQuotationNote) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuotationNote quotationNote = mapper.map(requestQuotationNote, QuotationNote.class);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(date);
        quotationNote.setQuotationNoteDate(currentDate);

        quotationNoteRepository.save(quotationNote);
    }

    /* pr테스트 */
    @Override
    public List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(long quotationId) {
        List<QuotationNote> quotationNotes = quotationNoteRepository.findByQuotationQuotationId(quotationId);

        return quotationNotes.stream().map(quotationNote -> mapper
                .map(quotationNote, ResponseFindAllQuotationNotesDTO.class))
                .collect(Collectors.toList());
    }
}
