package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.repository.ContractProductRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.CallSite;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Contract registContract(ContractDTO contractDTO) {

        Date date = new Date();
        SimpleDateFormat dateFormatForCode = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormatForRegist = new SimpleDateFormat("yyyy-MM-dd");

        // contractCode에 들어갈 날짜 형식
        String dateForCode = dateFormatForCode.format(date);

        // registDate에 들어갈 날짜 형식
        String registDate = dateFormatForRegist.format(date);
        contractDTO.setContractDate(registDate);

        String contractCode;
        do {
            int random = (int) (Math.random() * 999) + 1;
            if (0 <= random && random <= 9) {
                contractCode = "CT-" + dateForCode + "00" + random;
            } else if (10 <= random && random <= 99) {
                contractCode = "CT-" + dateForCode + "0" + random;
            } else {
                contractCode = "CT-" + dateForCode + random;
            }
        } while (isContractCodeDuplicate(contractCode));

        contractDTO.setContractCode(contractCode);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Contract newContract = modelMapper.map(contractDTO, Contract.class);

        newContract = contractRepository.save(newContract);

        return newContract;
    }

    private boolean isContractCodeDuplicate(String contractCode) {
//        for (ContractDTO contractDTO: contractList) {
//            if(contractDTO.getContractCode().equals(contractCode)) {
//                return true;
//            }
//        }
//        return false;

        return contractRepository.existsByContractCode(contractCode);
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
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계약서입니다."));

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
