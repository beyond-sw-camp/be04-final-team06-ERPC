package com.cineverse.erpc.access;

import com.cineverse.erpc.access.aggregate.AccessRequest;
import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.access.dto.*;
import com.cineverse.erpc.access.repo.AccessRequestRepository;
import com.cineverse.erpc.access.repo.EmployeeAccessRepository;
import com.cineverse.erpc.access.service.AccessService;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccessTests {
    EmployeeRepository employeeRepository;
    AccessRequestRepository accessRequestRepository;
    EmployeeAccessRepository employeeAccessRepository;
    AccessService accessService;

    @Autowired
    public AccessTests(EmployeeRepository employeeRepository,
                       AccessRequestRepository accessRequestRepository,
                       EmployeeAccessRepository employeeAccessRepository,
                       AccessService accessService) {
        this.employeeRepository = employeeRepository;
        this.accessRequestRepository = accessRequestRepository;
        this.employeeAccessRepository = employeeAccessRepository;
        this.accessService = accessService;
    }

    @Test
    @Transactional
    @DisplayName("권한신청 성공 테스트")
    public void successRegistAccessRequestTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));
        AccessRight accessRight = new AccessRight();
        accessRight.setAccessId(1);
        accessRight.setAccessRight("테스트 권한");


        RequestAccessRequestDTO requestAccess = RequestAccessRequestDTO.builder()
                .employee(employee)
                .accessRight(accessRight)
                .build();

        ResponseAccessRequestDTO responseAccessRequest = accessService.requestAccess(requestAccess);

        assertThat(requestAccess.getEmployee()).isEqualTo(responseAccessRequest.getEmployee());
        assertThat(requestAccess.getAccessRight()).isEqualTo(responseAccessRequest.getAccessRight());
        assertThat(responseAccessRequest.getAccessRequestDate()).isNotNull();
        assertThat(responseAccessRequest.getAccessRequestStatus()).isEqualTo("N");
    }

    @Test
    @Transactional
    @DisplayName("권한신청 단일조회 성공 테스트")
    public void successFindAccessRequestByIdTest() {
        AccessRequest accessRequest = accessRequestRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 권한신청입니다."));

        ResponseFindAccessRequestDTO responseFindAccess = accessService.findAccessRequestById(Long.valueOf(1));

        assertThat(accessRequest.getAccessRequestDate()).isEqualTo(responseFindAccess.getAccessRequestDate());
        assertThat(accessRequest.getAccessRequestStatus()).isEqualTo(responseFindAccess.getAccessRequestStatus());
        assertThat(accessRequest.getAccessRight()).isEqualTo(responseFindAccess.getAccessRight());
        assertThat(accessRequest.getEmployee()).isEqualTo(responseFindAccess.getEmployee());
    }

    @Test
    @Transactional
    @DisplayName("권한신청 전체조회 성공 테스트")
    public void successFindAccessRequestListTest() {
        List<ResponseFindAllAccessRequestDTO> accessRequests = accessService.findAllAccessRequest();

        assertThat(accessRequests).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("보유권한 조회 성공 테스트")
    public void successFindEmployeeAccessListTest() {
        List<ResponseFindEmployeesAccessDTO> employeeAccesses = accessService.findEmployeesAccess(Long.valueOf(3));

        assertThat(employeeAccesses).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("권한신청 처리 성공 테스트")
    public void successAddAccessTest() {
        Employee employee = employeeRepository.findById(Long.valueOf(1))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사원입니다."));

        AccessRight accessRight = new AccessRight();
        accessRight.setAccessId(1);
        accessRight.setAccessRight("테스트 권한");

        AccessRight accessRight2 = new AccessRight();
        accessRight2.setAccessId(2);
        accessRight2.setAccessRight("테스트 권한2");

        List<AccessRight> accessRightList = new ArrayList<>();
        accessRightList.add(accessRight);
        accessRightList.add(accessRight2);

        RequestAddAccessDTO addAccess = RequestAddAccessDTO.builder()
                .employee(employee)
                .accessRight(accessRightList)
                .build();

        ResponseAddAccessDTO responseAddAccess = accessService.addAccess(addAccess);

        assertThat(addAccess.getEmployee()).isEqualTo(responseAddAccess.getEmployee());
        assertThat(addAccess.getAccessRight().get(0)).isEqualTo(responseAddAccess.getAccessRight().get(0));
        assertThat(addAccess.getAccessRight().get(1)).isEqualTo(responseAddAccess.getAccessRight().get(1));
    }
}
