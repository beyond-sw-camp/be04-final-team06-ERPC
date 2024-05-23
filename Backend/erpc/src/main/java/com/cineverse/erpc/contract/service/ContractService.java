package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import org.springframework.web.multipart.MultipartFile;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContractService {
    Contract registContract(ContractDTO newContract, MultipartFile[] files);

    Contract modifyContract(long contractId, ContractDTO contract, MultipartFile[] files);

    List<Contract> findContractList();

    ContractDTO findContractById(long contractId);

    List<ContractProduct> findContractProductList();

    ContractDeleteRequest requestDeleteContract(ContractDeleteRequestDTO deleteContract);

    ContractDTO findContractByContractCode(String contractCode);

}
