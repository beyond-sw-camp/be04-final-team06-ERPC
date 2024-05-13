package com.cineverse.erpc.quotation.quotation.controller;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.RequestRegistQuotationDTO;
import com.cineverse.erpc.quotation.quotation.dto.ResponseFindQuotationDTO;
import com.cineverse.erpc.quotation.quotation.dto.ResponseRegistQuotationDTO;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotation")
public class QuotationController {
    private ModelMapper mapper;
    private QuotationService quotationService;

    @Autowired
    public QuotationController(ModelMapper mapper, QuotationService quotationService) {
        this.mapper = mapper;
        this.quotationService = quotationService;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseRegistQuotationDTO> registQuotation(
            @RequestBody RequestRegistQuotationDTO quotation) {

        quotationService.registQuotation(quotation);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistQuotationDTO responseRegistQuotation = mapper.map(quotation, ResponseRegistQuotationDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistQuotation);
    }

    @GetMapping("/{quotationId}")
    public Quotation fincQuotationByQuotationId(@PathVariable long quotationId) {
        Quotation quotation = quotationService.findQuotationById(quotationId);

        return quotation;
    }
}
