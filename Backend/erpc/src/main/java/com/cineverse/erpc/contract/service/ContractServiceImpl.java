package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.repository.ContractProductRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ModelMapper modelMapper;
    private final ContractRepository contractRepository;
    private final ContractProductRepository contractProductRepository;

    @Autowired
    public ContractServiceImpl(ModelMapper modelMapper, ContractRepository contractRepository, ContractProductRepository contractProductRepository) {
        this.modelMapper = modelMapper;
        this.contractRepository = contractRepository;
        this.contractProductRepository = contractProductRepository;
    }

    @Override
    @Transactional
    public Contract registContract(ContractDTO newContract) {
        return null;
    }

    @Override
    @Transactional
    public Contract modifyContract(Long contractId, ContractDTO contract) {
        return null;
    }

    @Override
    public List<Contract> findContractList() {
        List<Contract> contractList = contractRepository.findByContractDeleteDateIsNull();

        return contractList.stream().map(contract -> modelMapper
                        .map(contract, Contract.class))
                        .collect(Collectors.toList());
    }

    @Override
    public ContractDTO findContractById(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(EntityNotFoundException::new);

        contractRepository.save(contract);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);

        return contractDTO;
    }

    @Override
    public List<ContractProduct> findContractProductList() {
        List<ContractProduct> contractProductList = contractProductRepository.findAll();

        return contractProductList.stream().map(contractProduct -> modelMapper
                .map(contractProduct, ContractProduct.class))
                .collect(Collectors.toList());
    }
}
