package com.cineverse.erpc.quotation.quotation.controller;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.*;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
            @RequestPart("quotation") String quotationJson,
            @RequestPart(value = "files", required = false)MultipartFile[] files) throws JsonProcessingException {

        String utf8Json = new String(quotationJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();

        RequestRegistQuotationDTO newQuotation = objectMapper.readValue(utf8Json, RequestRegistQuotationDTO.class);

        quotationService.registQuotation(newQuotation, files);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistQuotationDTO responseRegistQuotation = mapper.map(newQuotation, ResponseRegistQuotationDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistQuotation);
    }

    @GetMapping("/{quotationId}")
    public Quotation findQuotationByQuotationId(@PathVariable long quotationId) {
        Quotation quotation = quotationService.findQuotationById(quotationId);

        return quotation;
    }

    @GetMapping()
    public List<QuotationDTO> findAllQuotations() {
        List<QuotationDTO> quotations = quotationService.findAllQuotations();

        return quotations;
    }

    @PatchMapping("/modify/{quotationId}")
    public ResponseEntity<ResponseModifyQuotationDTO> modifyQuotation(@PathVariable long quotationId,
                                                                      @RequestBody RequestModifyQuotationDTO quotation){
        ResponseModifyQuotationDTO  responseModifyQuotationDTO =
                quotationService.modifyQuotation(quotationId, quotation);

        return ResponseEntity.ok().body(responseModifyQuotationDTO);
    }


    @PostMapping("/delete")
    public ResponseEntity<ResponseDeleteQuotation> deleteQuotation(
            @RequestBody RequestDeleteQuotation requestDeleteQuotation) {

        ResponseDeleteQuotation responseDeleteQuotation = quotationService.deleteQuotation(requestDeleteQuotation);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteQuotation);
    }

    @GetMapping("/code")
    private ResponseQuotationByCode findQuotationByCode (@RequestParam String quotationCode) {

        return quotationService.findQuotationByCode(quotationCode);

    }
}
