package com.cineverse.erpc.access.service;

import com.cineverse.erpc.access.dto.*;

import java.util.List;

public interface AccessService {
    ResponseAccessRequestDTO requestAccess(RequestAccessRequestDTO requestAccess);

    ResponseFindAccessRequestDTO findAccessRequestById(long accessRequestId);

    List<ResponseFindAllAccessRequestDTO> findAllAccessRequest();

    List<ResponseFindEmployeesAccessDTO> findEmployeesAccess(long employeeId);

    ResponseAddAccessDTO addAccess(RequestAddAccessDTO addAccess);

}
