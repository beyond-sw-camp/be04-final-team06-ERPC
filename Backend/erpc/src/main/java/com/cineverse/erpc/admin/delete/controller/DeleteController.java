package com.cineverse.erpc.admin.delete.controller;

import com.cineverse.erpc.admin.delete.aggregate.SalesOppDelete;
import com.cineverse.erpc.admin.delete.service.DeleteService;
import com.cineverse.erpc.salesopp.opportunity.aggregate.SalesOpp;
import com.cineverse.erpc.salesopp.opportunity.dto.SalesOppDTO;
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
    public List<SalesOppDelete> findSalesOppDeleteRequest() {
        List<SalesOppDelete> salesOppDeleteRequestList = deleteService.findSalesOppDeleteRequestList();

        return salesOppDeleteRequestList;
    }

    /* 영업기회 삭제 요청 단일 조회 */
//    @GetMapping("/sales_opp/{salesOppDeleteRequestI}")
//    public


    /* 영업기회 삭제 요청 처리 */

}
