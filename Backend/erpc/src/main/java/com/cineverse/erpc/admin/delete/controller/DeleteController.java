package com.cineverse.erpc.admin.delete.controller;

import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOppDeleteRequest;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDeleteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delete")
public class DeleteController {

    private final DeleteService deleteService;

    @Autowired
    public DeleteController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    /* 영업기회 삭제 요청 전체 조회 */
    @GetMapping("/sales_opp")
    public List<SalesOppDeleteRequest> findSalesOppDeleteRequest() {
        List<SalesOppDeleteRequest> salesOppDeleteRequestList = deleteService.findSalesOppDeleteRequestList();

        return salesOppDeleteRequestList;
    }

    /* 영업기회 삭제 요청 단일 조회 */
    @GetMapping("/sales_opp/{salesOppDeleteRequestId}")
    public SalesOppDeleteRequestDTO findOppDeleteRequest(@PathVariable int salesOppDeleteRequestId) {
        SalesOppDeleteRequestDTO oppDeleteRequest = deleteService.findSalesOppDeleteRequestById(salesOppDeleteRequestId);

        return oppDeleteRequest;
    }

    /* 영업기회 삭제 요청 처리 */
    @PatchMapping("/status/{salesOppDeleteRequestId}")
    public ResponseEntity<SalesOppDeleteRequest> deleteSalesOpp(@RequestBody SalesOppDeleteRequestDTO deleteOpp, )


}
