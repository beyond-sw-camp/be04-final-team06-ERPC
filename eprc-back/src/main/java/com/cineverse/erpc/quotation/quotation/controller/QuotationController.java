package com.cineverse.erpc.quotation.quotation.controller;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.dto.*;
import com.cineverse.erpc.quotation.quotation.service.QuotationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/quotation")
public class QuotationController {

    private final ModelMapper mapper;
    private final QuotationService quotationService;

    @Autowired
    public QuotationController(ModelMapper mapper, QuotationService quotationService) {
        this.mapper = mapper;
        this.quotationService = quotationService;
    }

    @PostMapping(path = "/regist", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "견적서 등록", description = "새로운 견적서를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ResponseRegistQuotationDTO> registQuotation(
            @Parameter(description = "견적서 JSON 데이터", required = true) @RequestPart("quotation") String quotationJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        log.info("Received request to register quotation with data: {}", quotationJson);
        if (files != null) {
            log.info("Number of attached files: {}", files.length);
        } else {
            log.info("No files attached.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        RequestRegistQuotationDTO newQuotation;
        try {
            newQuotation = objectMapper.readValue(quotationJson, RequestRegistQuotationDTO.class);
            log.info("Parsed quotation JSON data successfully.");
        } catch (JsonProcessingException e) {
            log.error("Error parsing quotation JSON data: {}", e.getMessage());
            throw e;
        }

        try {
            quotationService.registQuotation(newQuotation, files);
            log.info("Quotation registered successfully.");
        } catch (Exception e) {
            log.error("Error registering quotation: {}", e.getMessage());
            throw new RuntimeException("Quotation registration failed.");
        }

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseRegistQuotationDTO responseRegistQuotation = mapper.map(newQuotation, ResponseRegistQuotationDTO.class);
        log.info("Mapped RequestRegistQuotationDTO to ResponseRegistQuotationDTO successfully.");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistQuotation);
    }

    @GetMapping("/{quotationId}")
    @Operation(summary = "견적서 단일 조회", description = "특정 견적서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "견적서를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public Quotation findQuotationByQuotationId(
            @Parameter(description = "견적서 ID", required = true) @PathVariable long quotationId) {
        log.info("Received request to find quotation with ID: {}", quotationId);
        Quotation quotation = quotationService.findQuotationById(quotationId);
        log.info("Quotation with ID {} found: {}", quotationId, quotation);
        return quotation;
    }

    @GetMapping()
    @Operation(summary = "견적서 전체 조회", description = "모든 견적서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<QuotationDTO> findAllQuotations() {
        log.info("Received request to find all quotations");
        List<QuotationDTO> quotations = quotationService.findAllQuotations();
        log.info("Found {} quotations", quotations.size());
        return quotations;
    }

    @PatchMapping(path = "/modify", consumes = {"multipart/form-data;charset=UTF-8"})
    @Operation(summary = "견적서 수정", description = "기존 견적서를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ResponseModifyQuotationDTO> modifyQuotation(
            @Parameter(description = "수정할 견적서 JSON 데이터", required = true)
            @RequestPart("quotation") String quotationJson,
            @Parameter(description = "첨부 파일들") @RequestPart(value = "files", required = false) MultipartFile[] files)
            throws JsonProcessingException {

        log.info("Received request to modify quotation with data: {}", quotationJson);
        if (files != null) {
            log.info("Number of attached files: {}", files.length);
        } else {
            log.info("No files attached.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        RequestModifyQuotationDTO requestModifyQuotationDTO;
        try {
            requestModifyQuotationDTO = objectMapper.readValue(quotationJson, RequestModifyQuotationDTO.class);
            log.info("Parsed modification quotation JSON data successfully.");
        } catch (JsonProcessingException e) {
            log.error("Error parsing modification quotation JSON data: {}", e.getMessage());
            throw e;
        }

        ResponseModifyQuotationDTO responseModifyQuotationDTO;
        try {
            responseModifyQuotationDTO = quotationService.modifyQuotation(requestModifyQuotationDTO, files);
            log.info("Quotation modified successfully.");
        } catch (Exception e) {
            log.error("Error modifying quotation: {}", e.getMessage());
            throw new RuntimeException("Quotation modification failed.");
        }

        return ResponseEntity.ok().body(responseModifyQuotationDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "견적서 삭제 요청", description = "견적서 삭제 요청을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "삭제 요청 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ResponseDeleteQuotation> deleteQuotation(
            @Parameter(description = "삭제 요청 데이터", required = true)
            @RequestBody RequestDeleteQuotation requestDeleteQuotation) {
        log.info("Received request to delete quotation with data: {}", requestDeleteQuotation);
        ResponseDeleteQuotation responseDeleteQuotation;
        try {
            responseDeleteQuotation = quotationService.deleteQuotation(requestDeleteQuotation);
            log.info("Quotation delete request registered successfully.");
        } catch (Exception e) {
            log.error("Error registering quotation delete request: {}", e.getMessage());
            throw new RuntimeException("Quotation delete request failed.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDeleteQuotation);
    }

    @GetMapping("/code")
    @Operation(summary = "견적서 코드 조회", description = "견적서 코드를 통해 견적서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "견적서를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseQuotationByCode findQuotationByCode(
            @Parameter(description = "견적서 코드", required = true) @RequestParam String quotationCode) {
        log.info("Received request to find quotation by code: {}", quotationCode);
        ResponseQuotationByCode quotation = quotationService.findQuotationByCode(quotationCode);
        log.info("Quotation with code {} found: {}", quotationCode, quotation);
        return quotation;
    }
}
