package com.cineverse.erpc.access.service;

import com.cineverse.erpc.access.aggregate.AccessRequest;
import com.cineverse.erpc.access.aggregate.AccessRight;
import com.cineverse.erpc.access.aggregate.EmployeeAccess;
import com.cineverse.erpc.access.dto.*;
import com.cineverse.erpc.access.repo.AccessRequestRepository;
import com.cineverse.erpc.access.repo.EmployeeAccessRepository;
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

    @Autowired
    public AccessServiceImpl(ModelMapper mapper,
                             AccessRequestRepository accessRequestRepository,
                             EmployeeAccessRepository employeeAccessRepository) {
        this.mapper = mapper;
        this.accessRequestRepository = accessRequestRepository;
        this.employeeAccessRepository = employeeAccessRepository;
    }

    @Override
    @Transactional
    public ResponseAccessRequestDTO requestAccess(RequestAccessRequestDTO requestAccess) {

        AccessRequest accessRequest = mapper.map(requestAccess, AccessRequest.class);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(date);

        accessRequest.setAccessRequestDate(registDate);
        accessRequest.setAccessRequestStatus("N");
        accessRequestRepository.save(accessRequest);

        return mapper.map(accessRequest, ResponseAccessRequestDTO.class);
    }

    @Override
    public ResponseFindAccessRequestDTO findAccessRequestById(long accessRequestId) {
        AccessRequest accessRequest = accessRequestRepository.findById(accessRequestId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 권한요청입니다."));

        return mapper.map(accessRequest, ResponseFindAccessRequestDTO.class);
    }

    @Override
    public List<ResponseFindAllAccessRequestDTO> findAllAccessRequest() {
        List<AccessRequest> accessRequests = accessRequestRepository.findAll();

        return accessRequests.stream().map(accessRequest -> mapper
                        .map(accessRequest, ResponseFindAllAccessRequestDTO.class))
                .collect(Collectors.toList());
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

        employeeAccessRepository.deleteByEmployeeEmployeeId(addAccess.getEmployee().getEmployeeId());

        for (int i = 0; i < addAccess.getAccessRight().size(); i++) {
            EmployeeAccess employeeAccess = new EmployeeAccess();

            employeeAccess.setEmployee(addAccess.getEmployee());
            employeeAccess.setAccessRight(addAccess.getAccessRight().get(i));

            employeeAccessRepository.save(employeeAccess);
        }

        ResponseAddAccessDTO responseAddAccess = mapper.map(addAccess, ResponseAddAccessDTO.class);
        return responseAddAccess;
    }
}

