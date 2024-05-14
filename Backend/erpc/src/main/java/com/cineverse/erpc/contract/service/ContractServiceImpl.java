package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractProductDTO;
import com.cineverse.erpc.contract.repository.ContractProductRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.product.aggregate.entity.Product;
import com.cineverse.erpc.product.repository.ProductRepository;
import com.cineverse.erpc.quotation.quotation.repo.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ModelMapper modelMapper;
    private final ContractRepository contractRepository;
    private final ContractProductRepository contractProductRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ContractServiceImpl(ModelMapper modelMapper,
                               ContractRepository contractRepository,
                               ContractProductRepository contractProductRepository,
                               TransactionRepository transactionRepository,
                               ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.contractRepository = contractRepository;
        this.contractProductRepository = contractProductRepository;
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Contract registContract(ContractDTO contractDTO) {

        Date date = new Date();
        SimpleDateFormat dateFormatForCode = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormatForRegist = new SimpleDateFormat("yyyy-MM-dd");
        String codeDate = dateFormatForCode.format(date);
        String registDate = dateFormatForRegist.format(date);

        contractDTO.setContractDate(registDate);

        String contractCode;
        do {
            int random = (int) (Math.random() * 999) + 1;
            if (0 <= random && random <= 9) {
                contractCode = "CT-" + codeDate + "00" + random;
            } else if (10 <= random && random <= 99) {
                contractCode = "CT-" + codeDate + "0" + random;
            } else {
                contractCode = "CT-" + codeDate + random;
            }
        } while (isContractCodeDuplicate(contractCode));

        contractDTO.setContractCode(contractCode);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Contract newContract = modelMapper.map(contractDTO, Contract.class);

        // 사실상 최종 상태를 유지하며 람다 대신 일반 반복문 사용
        if (contractDTO.getContractProduct() != null) {
            List<ContractProduct> contractProducts = new ArrayList<>();
            for (ContractProduct cpDto : contractDTO.getContractProduct()) {
                ContractProduct cp = modelMapper.map(cpDto, ContractProduct.class);
                Product product = productRepository.findById(cpDto.getProduct().getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                cp.setProduct(product);
                cp.setContract(newContract);  // 각 ContractProduct에 Contract 설정
                contractProducts.add(cp);
            }
            newContract.setContractProduct(contractProducts);
        }

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

        List<ContractProduct> contractProductList =
                contractProductRepository.findByContractContractId(contractId);

        contract.setContractProduct(contractProductList);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);

        return contractDTO;

//        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
//        return contractDTO;
    }

    @Override
    public List<ContractProduct> findContractProductList() {
        List<ContractProduct> contractProductList = contractProductRepository.findAll();

        return contractProductList.stream().map(contractProduct -> modelMapper
                .map(contractProduct, ContractProduct.class))
                .collect(Collectors.toList());
    }
}
