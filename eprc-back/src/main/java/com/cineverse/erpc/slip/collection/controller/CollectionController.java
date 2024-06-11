package com.cineverse.erpc.slip.collection.controller;

import com.cineverse.erpc.slip.collection.aggregate.Collection;
import com.cineverse.erpc.slip.collection.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("")
    @Operation(summary = "수금 전체 조회", description = "등록된 모든 수금 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "통신 오류")
    })
    public List<Collection> findCollectionList() {
        List<Collection> collectionList = collectionService.findCollectionList();
        return collectionList;
    }
}
