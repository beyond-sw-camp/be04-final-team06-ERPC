package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContractService {
    Contract registContract(ContractDTO newContract);

    Contract modifyContract(Long contractId, ContractDTO contract);

    List<Contract> findContractList();

    ContractDTO findContractById(Long contractId);

    List<ContractProduct> findContractProductList();

    ContractDeleteRequest requestDeleteContract(ContractDeleteRequestDTO deleteContract);

    ContractDTO findContractByContractCode(String contractCode);

}
