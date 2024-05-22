package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContractService {
    Contract registContract(ContractDTO newContract, MultipartFile[] files);

    Contract modifyContract(Long contractId, ContractDTO contract);

    List<Contract> findContractList();

    ContractDTO findContractById(Long contractId);

    List<ContractProduct> findContractProductList();
}
