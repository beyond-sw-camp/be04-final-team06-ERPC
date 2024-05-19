package com.cineverse.erpc.access.controller;

import com.cineverse.erpc.access.dto.*;
import com.cineverse.erpc.access.service.AccessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")
public class AccessController {
    private AccessService accessService;
    private ModelMapper mapper;

    @Autowired
    public AccessController(AccessService accessService, ModelMapper mapper) {
        this.accessService = accessService;
        this.mapper = mapper;
    }

    /* 권한 신청 */
    @PostMapping("/access_request")
    public ResponseEntity<ResponseAccessRequestDTO> registAccessRequest(
            @RequestBody RequestAccessRequestDTO requestAccess) {
        ResponseAccessRequestDTO responseAccess = accessService.requestAccess(requestAccess);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccess);
    }
    /* 권한신청 조회 */
    @GetMapping("{accessRequestId}")
    public ResponseEntity<ResponseFindAccessRequestDTO> findAccessRequestById(@PathVariable long accessRequestId) {
        ResponseFindAccessRequestDTO responseFindAccessRequest
                = accessService.findAccessRequestById(accessRequestId);

        return ResponseEntity.ok().body(responseFindAccessRequest);
    }

    @GetMapping("/list")
    public List<ResponseFindAllAccessRequestDTO> findAllAccessRequest() {

        return accessService.findAllAccessRequest();
    }
    /* 보유권한 조회 */
    @GetMapping("find_access/{employeeId}")
    public List<ResponseFindEmployeesAccessDTO> findEmployeesAccess(@PathVariable long employeeId) {
        return accessService.findEmployeesAccess(employeeId);
    }

    /* 권한신청 처리 */
    @PostMapping("/add_access")
    public ResponseEntity<ResponseAddAccessDTO> addAccess(@RequestBody RequestAddAccessDTO addAccess) {
       ResponseAddAccessDTO responseAddAccess = accessService.addAccess(addAccess);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseAddAccess);
    }

}
