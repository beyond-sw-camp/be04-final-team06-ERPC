package com.cineverse.erpc.access.service;

import com.cineverse.erpc.access.aggregate.AccessRequest;
import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.access.aggregate.EmployeeAccess;
import com.cineverse.erpc.access.dto.*;
import com.cineverse.erpc.access.repo.AccessRequestRepository;
import com.cineverse.erpc.access.repo.EmployeeAccessRepository;
import com.cineverse.erpc.employee.aggregate.Employee;
import com.cineverse.erpc.employee.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccessServiceImpl implements AccessService {

    private ModelMapper mapper;
    private AccessRequestRepository accessRequestRepository;
    private EmployeeAccessRepository employeeAccessRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public AccessServiceImpl(ModelMapper mapper,
                             AccessRequestRepository accessRequestRepository,
                             EmployeeAccessRepository employeeAccessRepository,
                             EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.accessRequestRepository = accessRequestRepository;
        this.employeeAccessRepository = employeeAccessRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public ResponseAccessRequestDTO requestAccess(RequestAccessRequestDTO requestAccess) {


        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

//        AccessRequest accessRequest = new AccessRequest();
//        accessRequest.setAccessRequestDate(registDate);
//        accessRequest.setAccessRequestStatus("N");
//        accessRequest.setEmployee(requestAccess.getEmployee());

        for (int i = 0; i < requestAccess.getAccessRight().size(); i++) {
            AccessRequest accessRequest = new AccessRequest();
            accessRequest.setAccessRequestDate(registDate);
            accessRequest.setAccessRequestStatus("N");
            accessRequest.setEmployee(requestAccess.getEmployee());
            accessRequest.setAccessRight(requestAccess.getAccessRight().get(i));
            accessRequestRepository.save(accessRequest);
        }
        ResponseAccessRequestDTO responseAccessRequest = new ResponseAccessRequestDTO();
        responseAccessRequest.setAccessRequestDate(registDate);
        responseAccessRequest.setAccessRequestStatus("N");
        responseAccessRequest.setEmployee(requestAccess.getEmployee());

        responseAccessRequest.setAccessRight(requestAccess.getAccessRight());

        return responseAccessRequest;
    }

    @Override
    public ResponseFindAccessRequestDTO findAccessRequestById(long accessRequestId) {
        AccessRequest accessRequest = accessRequestRepository.findById(accessRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 권한요청입니다."));

        return mapper.map(accessRequest, ResponseFindAccessRequestDTO.class);
    }

    @Override
    public List<ResponseFindAllAccessRequestDTO> findAllAccessRequest() {
        List<AccessRequest> accessRequests = accessRequestRepository.findAllByAccessRequestStatus("N");

        return accessRequests.stream().map(accessRequest -> mapper
                        .map(accessRequest, ResponseFindAllAccessRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseFindAccessRequestDTO accessProcess(long accessRequestId) {
        AccessRequest accessRequest = accessRequestRepository.findById(accessRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 권한신청입니다."));

        accessRequest.setAccessRequestStatus("Y");

        accessRequestRepository.save(accessRequest);

        return mapper.map(accessRequest, ResponseFindAccessRequestDTO.class);
    }

    @Override
    public List<ResponseFindEmployeesAccessDTO> findEmployeesAccess(long employeeId) {
        List<EmployeeAccess> employeeAccesses = employeeAccessRepository.findAllByEmployeeEmployeeId(employeeId);

        return employeeAccesses.stream().map(employeeAccess -> mapper
                        .map(employeeAccess, ResponseFindEmployeesAccessDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseAddAccessDTO addAccess(RequestAddAccessDTO addAccess) {
        Employee employee = employeeRepository.findByEmployeeCode(addAccess.getEmployee().getEmployeeCode());

        employeeAccessRepository.deleteByEmployeeEmployeeId(employee.getEmployeeId());

        for (int i = 0; i < addAccess.getAccessRight().size(); i++) {
            EmployeeAccess employeeAccess = new EmployeeAccess();

            employeeAccess.setEmployee(employee);
            employeeAccess.setAccessRight(addAccess.getAccessRight().get(i));

            employeeAccessRepository.save(employeeAccess);
        }

        addAccess.setEmployee(employee);

        ResponseAddAccessDTO responseAddAccess = mapper.map(addAccess, ResponseAddAccessDTO.class);
        return responseAddAccess;
    }

    @Override
    public List<ResponseFindEmployeesAccessDTO> findEmployeesAccessByEmployeeCode(String employeeCode) {
        List<EmployeeAccess> employeeAccesses = employeeAccessRepository.findAllByEmployeeEmployeeCode(employeeCode);

        return employeeAccesses.stream().map(employeeAccess -> mapper
                .map(employeeAccess, ResponseFindEmployeesAccessDTO.class))
                .collect(Collectors.toList());
    }
}

