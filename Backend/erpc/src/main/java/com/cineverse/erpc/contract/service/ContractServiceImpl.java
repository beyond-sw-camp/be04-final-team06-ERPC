package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.repository.ContractProductRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ModelMapper modelMapper;
    private final ContractRepository contractRepository;
    private final ContractProductRepository contractProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ContractServiceImpl(ModelMapper modelMapper,
                               ContractRepository contractRepository,
                               ContractProductRepository contractProductRepository,
                               ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.contractRepository = contractRepository;
        this.contractProductRepository = contractProductRepository;
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

        if (contractDTO.getContractProduct() != null) {
            List<ContractProduct> contractProducts = new ArrayList<>();
            for (ContractProduct cpDTO : contractDTO.getContractProduct()) {
                ContractProduct cp = modelMapper.map(cpDTO, ContractProduct.class);
                Product product = productRepository.findById(cpDTO.getProduct().getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                cp.setProduct(product);
                cp.setContract(newContract);
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
    public Contract modifyContract(Long contractId, ContractDTO contract) throws UsernameNotFoundException {
        Optional<Contract> optionalContract = contractRepository.findById(contractId);

        if(optionalContract.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 계약서입니다.");
        }

        Contract modCont = optionalContract.get();

        if (contract.getContractNote() != null) {
            modCont.setContractNote(contract.getContractNote());
        }

        if (contract.getContractTotalPrice() != null) {
            modCont.setContractTotalPrice(contract.getContractTotalPrice());
        }

        if (contract.getContractDueDate() != null) {
            modCont.setContractDueDate(contract.getContractDueDate());
        }

        if (contract.getDownPayment() != null) {
            modCont.setDownPayment(contract.getDownPayment());
        }

        if (contract.getProgressPayment() != null) {
            modCont.setProgressPayment(contract.getProgressPayment());
        }

        if (contract.getBalance() != null) {
            modCont.setBalance(contract.getBalance());
        }

        if (contract.getEmployee() != null) {
            modCont.setEmployee(contract.getEmployee());
        }

        if (contract.getAccount() != null) {
            modCont.setAccount(contract.getAccount());
        }

        if (contract.getWarehouse() != null) {
            modCont.setWarehouse(contract.getWarehouse());
        }

        return contractRepository.save(modCont);
    }

    private ContractProduct modifyContractProduct(ContractProduct product, Contract modifyContract) {
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
    }

    @Override
    public List<ContractProduct> findContractProductList() {
        List<ContractProduct> contractProductList = contractProductRepository.findAll();

        return contractProductList.stream().map(contractProduct -> modelMapper
                .map(contractProduct, ContractProduct.class))
                .collect(Collectors.toList());
    }
}
