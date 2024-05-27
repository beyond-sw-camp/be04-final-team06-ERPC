package com.cineverse.erpc.quotation.note.controller;

import com.cineverse.erpc.quotation.note.dto.RequestRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseDeleteQuotationNote;
import com.cineverse.erpc.quotation.note.dto.ResponseFindAllQuotationNotesDTO;
import com.cineverse.erpc.quotation.note.dto.ResponseRegistQuotationNoteDTO;
import com.cineverse.erpc.quotation.note.service.QuotationNoteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotation_note")
public class QuotationNoteController {
    private ModelMapper mapper;
    private QuotationNoteService quotationNoteService;

    @Autowired
    public QuotationNoteController(ModelMapper mapper, QuotationNoteService quotationNoteService) {
        this.mapper = mapper;
        this.quotationNoteService = quotationNoteService;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistQuotationNoteDTO> registQuotationNote(
            @RequestBody RequestRegistQuotationNoteDTO requestQuotationNote) {

        quotationNoteService.registQuotationNote(requestQuotationNote);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistQuotationNoteDTO responseQuotationNote =
                mapper.map(requestQuotationNote, ResponseRegistQuotationNoteDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseQuotationNote);
    }

    @GetMapping("/{quotationId}")
    public List<ResponseFindAllQuotationNotesDTO> findAllQuotationNotes(@PathVariable long quotationId) {
        List<ResponseFindAllQuotationNotesDTO> quotationNotes =
                quotationNoteService.findAllQuotationNotes(quotationId);

        return quotationNotes;
    }

    @PatchMapping("/delete/{quotationNoteId}")
    public ResponseEntity<ResponseDeleteQuotationNote> deleteQuotationNote(@PathVariable long quotationNoteId) {

        ResponseDeleteQuotationNote responseDeleteQuotationNote =
                quotationNoteService.deleteQuotationNote(quotationNoteId);

        return ResponseEntity.status(HttpStatus.OK).body(responseDeleteQuotationNote);
    }

}
