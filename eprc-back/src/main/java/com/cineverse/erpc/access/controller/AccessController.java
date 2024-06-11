package com.cineverse.erpc.access.controller;

import com.cineverse.erpc.access.dto.*;
import com.cineverse.erpc.access.service.AccessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access")
public class AccessController {
    private final AccessService accessService;

    @Autowired
    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/access_request")
    @Operation(summary = "사원 권한 신청", description = "접근 권한을 신청합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력 값 불일치")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public ResponseEntity<ResponseAccessRequestDTO> registAccessRequest(
            @Parameter(required = true, description = "권한신청 요청")
            @RequestBody RequestAccessRequestDTO requestAccess) {
        ResponseAccessRequestDTO responseAccess = accessService.requestAccess(requestAccess);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccess);
    }

    @GetMapping("/{accessRequestId}")
    @Operation(summary = "권한신청 단일조회", description = "권한 신청을 단일조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public ResponseEntity<ResponseFindAccessRequestDTO> findAccessRequestById(
            @Parameter(required = true, description = "권한신청 고유번호")
            @PathVariable long accessRequestId) {
        ResponseFindAccessRequestDTO responseFindAccessRequest
                = accessService.findAccessRequestById(accessRequestId);

        return ResponseEntity.ok().body(responseFindAccessRequest);
    }

    @GetMapping("/list")
    @Operation(summary = "권한신청 전체조회", description = "전체 권한 신청을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public List<ResponseFindAllAccessRequestDTO> findAllAccessRequest() {

        return accessService.findAllAccessRequest();
    }

    @GetMapping("/process")
    @Operation(summary = "권한신청 상태 변경", description = "권한신청 상태를 변경합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public ResponseFindAccessRequestDTO accessProcess(@RequestParam long accessRequestId) {

        return accessService.accessProcess(accessRequestId);
    }


    @GetMapping("/find_access/{employeeId}")
    @Operation(summary = "보유권한 조회", description = "사원이 보유한 권한을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public List<ResponseFindEmployeesAccessDTO> findEmployeesAccess(
            @Parameter(required = true, description = "사원 고유번호")
            @PathVariable long employeeId) {
        return accessService.findEmployeesAccess(employeeId);
    }


    @PostMapping("/add_access")
    @Operation(summary = "권한신청 처리", description = "사원이 신청한 권한을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public ResponseEntity<ResponseAddAccessDTO> addAccess(
            @Parameter(required = true, description = "권한처리 요청")
            @RequestBody RequestAddAccessDTO addAccess) {

       ResponseAddAccessDTO responseAddAccess = accessService.addAccess(addAccess);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseAddAccess);
    }


    @GetMapping("/find_access")
    @Operation(summary = "사번으로 보유권한 조회", description = "사번으로 사원 보유 권한을 조회합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "403", description = "입력값 불일치")
    @ApiResponse(responseCode = "500", description = "통신오류")
    public List<ResponseFindEmployeesAccessDTO> findEmployeesAccessByEmployeeCode(
            @Parameter(required = true, description = "사번")
            @RequestParam String employeeCode) {
        return accessService.findEmployeesAccessByEmployeeCode(employeeCode);
    }
}
