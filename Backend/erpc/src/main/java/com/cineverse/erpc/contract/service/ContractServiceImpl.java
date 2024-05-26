package com.cineverse.erpc.contract.service;

import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.contract.aggregate.ContractDeleteRequest;
import com.cineverse.erpc.contract.aggregate.ContractProduct;
import com.cineverse.erpc.contract.dto.ContractDTO;
import com.cineverse.erpc.contract.dto.ContractDeleteRequestDTO;
import com.cineverse.erpc.contract.dto.ContractProductDTO;
import com.cineverse.erpc.contract.repository.ContractDeleteRequestRepository;
import com.cineverse.erpc.contract.repository.ContractProductRepository;
import com.cineverse.erpc.contract.repository.ContractRepository;
import com.cineverse.erpc.file.service.FileUploadService;
import com.cineverse.erpc.product.aggregate.Product;
import com.cineverse.erpc.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ModelMapper modelMapper;
    private final ContractRepository contractRepository;
    private final ContractProductRepository contractProductRepository;
    private final ProductRepository productRepository;
    private final FileUploadService fileUploadService;
    private final ContractDeleteRequestRepository contractDeleteRequestRepository;

    @Autowired
    public ContractServiceImpl(ModelMapper modelMapper,
                               ContractRepository contractRepository,
                               ContractProductRepository contractProductRepository,
                               FileUploadService fileUploadService,
                               ProductRepository productRepository,
                               ContractDeleteRequestRepository contractDeleteRequestRepository)
    {
        this.modelMapper = modelMapper;
        this.contractRepository = contractRepository;
        this.contractProductRepository = contractProductRepository;
        this.productRepository = productRepository;
        this.fileUploadService = fileUploadService;
        this.contractDeleteRequestRepository = contractDeleteRequestRepository;
    }

    @Override
    @Transactional
    public Contract registContract(ContractDTO contractDTO, MultipartFile[] files) {

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
            for (ContractProductDTO cpDTO : contractDTO.getContractProduct()) {
                ContractProduct cp = modelMapper.map(cpDTO, ContractProduct.class);
                Product product = productRepository.findById(cpDTO.getProduct().getProductId())
                        .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
                cp.setProduct(product);
                cp.setContract(newContract);
                contractProducts.add(cp);
            }
            newContract.setContractProduct(contractProducts);
        }

        newContract = contractRepository.save(newContract);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileUploadService.saveContractFile(file, newContract);
            }
        }
        return newContract;
    }

    private boolean isContractCodeDuplicate(String contractCode) {
        return contractRepository.existsByContractCode(contractCode);
    }

    @Override
    @Transactional
    public Contract modifyContract(long contractId, ContractDTO contractDTO, MultipartFile[] files) {
        Contract existingContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 계약서입니다."));

        contractProductRepository.deleteByContractContractId(contractId);

        if (contractDTO.getContractNote() != null) {
            existingContract.setContractNote(contractDTO.getContractNote());
        }
        if (contractDTO.getContractTotalPrice() != null) {
            existingContract.setContractTotalPrice(contractDTO.getContractTotalPrice());
        }
        if (contractDTO.getContractDueDate() != null) {
            existingContract.setContractDueDate(contractDTO.getContractDueDate());
        }
        if (contractDTO.getDownPayment() != null) {
            existingContract.setDownPayment(contractDTO.getDownPayment());
        }
        if (contractDTO.getProgressPayment() != null) {
            existingContract.setProgressPayment(contractDTO.getProgressPayment());
        }
        if ((contractDTO.getContractCategory()) != null) {
            existingContract.setContractCategory(contractDTO.getContractCategory());
        }
        if (contractDTO.getBalance() != null) {
            existingContract.setBalance(contractDTO.getBalance());
        }
        if (contractDTO.getEmployee() != null) {
            existingContract.setEmployee(contractDTO.getEmployee());
        }
        if (contractDTO.getAccount() != null) {
            existingContract.setAccount(contractDTO.getAccount());
        }
        if (contractDTO.getWarehouse() != null) {
            existingContract.setWarehouse(contractDTO.getWarehouse());
        }

        List<ContractProduct> newContractProducts = contractDTO.getContractProduct().stream()
                .map(cpDTO -> {
                    ContractProduct cp = modelMapper.map(cpDTO, ContractProduct.class);
                    Product product = productRepository.findById(cpDTO.getProduct().getProductId())
                            .orElseThrow(() -> new RuntimeException("존재하지 않는 상품입니다."));
                    cp.setProduct(product);
                    cp.setContract(existingContract);
                    return cp;
                }).collect(Collectors.toList());

        existingContract.setContractProduct(newContractProducts);

        if (files != null && files.length > 0) {
            fileUploadService.deleteFilesByContract(existingContract);

            for (MultipartFile file : files) {
                fileUploadService.saveContractFile(file, existingContract);
            }
        }

        return contractRepository.save(existingContract);
    }

    @Override
    @Transactional
    public ContractDeleteRequest requestDeleteContract(ContractDeleteRequestDTO deleteContract) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ContractDeleteRequest deleteReqContract = modelMapper.map(deleteContract, ContractDeleteRequest.class);
        deleteReqContract = contractDeleteRequestRepository.save(deleteReqContract);

        deleteReqContract.setContractDeleteRequestStatus('N');

        return deleteReqContract;
    }

    @Override
    public List<Contract> findContractList() {
        List<Contract> contractList = contractRepository.findByContractDeleteDateIsNullOrderByContractIdDesc();

        return contractList.stream().map(contract -> modelMapper
                        .map(contract, Contract.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO findContractById(long contractId) {
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
    public ContractDTO findContractByContractCode(String contractCode) {
        Contract contract = contractRepository.findByContractCode(contractCode)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 계약서 코드입니다: " + contractCode));

        return modelMapper.map(contract, ContractDTO.class);
    }

    @Override
    public List<ContractProduct> findContractProductList() {
        List<ContractProduct> contractProductList = contractProductRepository.findAll();

        return contractProductList.stream().map(contractProduct -> modelMapper
                        .map(contractProduct, ContractProduct.class))
                .collect(Collectors.toList());
    }
}
