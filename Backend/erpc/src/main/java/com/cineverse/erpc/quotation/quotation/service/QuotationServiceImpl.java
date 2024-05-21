package com.cineverse.erpc.quotation.quotation.service;

import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.quotation.quotation.aggregate.QuotationProduct;
import com.cineverse.erpc.quotation.quotation.aggregate.Transaction;
import com.cineverse.erpc.quotation.quotation.dto.*;
import com.cineverse.erpc.quotation.quotation.repo.QuotationProductRepository;
import com.cineverse.erpc.quotation.quotation.repo.QuotationRepository;
import com.cineverse.erpc.quotation.quotation.repo.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QuotationServiceImpl implements QuotationService{

    private final ModelMapper mapper;
    private final QuotationRepository quotationRepository;
    private final QuotationProductRepository quotationProductRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public QuotationServiceImpl(ModelMapper mapper,
                                QuotationRepository quotationRepository,
                                QuotationProductRepository quotationProductRepository,
                                TransactionRepository transactionRepository) {
        this.mapper = mapper;
        this.quotationRepository = quotationRepository;
        this.quotationProductRepository = quotationProductRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public void registQuotation(RequestRegistQuotationDTO requestQuotation) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat codeFormat = new SimpleDateFormat("yyyyMMdd");
        String registDate = dateFormat.format(date);
        String registCode = codeFormat.format(date);

        String transactionCode;
        List<Transaction> transactionList = findTrasactionLists();

        do {
            int random = (int) (Math.random() * 999) + 1;
            if (0 <= random && random <= 9) {
                transactionCode = "TA-" + registCode + "00" + random;
            } else if (10 <= random && random <= 99) {
                transactionCode = "TA-" + registCode + "0" + random;
            } else {
                transactionCode = "TA-" + registCode + random;
            }
        } while (isTransactionCodeDuplicate(transactionList, transactionCode));

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(registDate);
        transaction.setTransactionCode(transactionCode);
        transactionRepository.save(transaction);

        String quotationCode;
        List<Quotation> quotationList = findQuotationLists();

        do {
            int random = (int) (Math.random() * 999) + 1;
            if (0 <= random && random <= 9) {
                quotationCode = "QT-" + registCode + "00" + random;
            } else if (10 <= random && random <= 99) {
                quotationCode = "QT-" + registCode + "0" + random;
            } else {
                quotationCode = "QT-" + registCode + random;
            }
        } while (isQuotationCodeDuplicate(quotationList, quotationCode));

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Quotation quotation = mapper.map(requestQuotation, Quotation.class);

        quotation.setTransaction(transaction);
        quotation.setQuotationDate(registDate);
        quotation.setQuotationCode(quotationCode);
        quotationRepository.save(quotation);

        for (QuotationProduct product : requestQuotation.getQuotationProduct()) {
            QuotationProduct quotationProduct = registQuotationProduct(product, quotation);
        }
    }

    private boolean isQuotationCodeDuplicate(List<Quotation> quotationList, String quotationCode) {
        for (Quotation quotation : quotationList) {
            if (quotation.getQuotationCode().equals(quotationCode)){
                return true;
            }
        }
        return false;
    }

    private List<Quotation> findQuotationLists() {
        List<Quotation> quotations = quotationRepository.findAll();

        return quotations.stream().map(quotation -> mapper
                        .map(quotation, Quotation.class))
                .collect(Collectors.toList());
    }

    private QuotationProduct registQuotationProduct(QuotationProduct product, Quotation quotation) {
        product.setQuotation(quotation);
        quotationProductRepository.save(product);

        return product;
    }

    private boolean isTransactionCodeDuplicate(List<Transaction> transactionList, String transactionCode) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionCode().equals(transactionCode)){
                return true;
            }
        }
        return false;
    }

    private List<Transaction> findTrasactionLists() {
        List<Transaction> transactions = transactionRepository.findAll();

        return transactions.stream().map(transaction -> mapper
                .map(transaction, Transaction.class))
                .collect(Collectors.toList());
    }

    @Override
    public Quotation findQuotationById(long quotationId) {
        Quotation quotation = quotationRepository.findById(quotationId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 견적서입니다."));

        List<QuotationProduct> quotationProducts =
                quotationProductRepository.findByQuotationQuotationId(quotationId);


        quotation.setQuotationProduct(quotationProducts);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseFindQuotationDTO responseFindQuotationDTO =
                mapper.map(quotation, ResponseFindQuotationDTO.class);

        return quotation;
    }

    @Override
    public List<QuotationDTO> findAllQuotations() {
        List<Quotation> quotations = quotationRepository.findAll();

        return quotations.stream().map(quotation -> mapper
                .map(quotation, QuotationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseModifyQuotationDTO modifyQuotation(long quotationId, RequestModifyQuotationDTO quotation) {
        Quotation modifyQuotation = quotationRepository.findById(quotationId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 견적서입니다."));

        if (!quotation.getQuotationProduct().isEmpty()) {
            quotationProductRepository.deleteAllByQuotationQuotationId(modifyQuotation.getQuotationId());
            for (QuotationProduct product : quotation.getQuotationProduct()) {
                QuotationProduct quotationProduct = modifyQuotationProduct(product, modifyQuotation);
            }
        }
        if (quotation.getQuotationNote() != null) {
            modifyQuotation.setQuotationNote(quotation.getQuotationNote());
        }
        if (quotation.getQuotationTotalCost() != 0) {
            modifyQuotation.setQuotationTotalCost(quotation.getQuotationTotalCost());
        }
        if (quotation.getQuotationDueDate() != null) {
            modifyQuotation.setQuotationDueDate(quotation.getQuotationDueDate());
        }
        if (quotation.getEmployee() != null) {
            modifyQuotation.setEmployee(quotation.getEmployee());
        }
        if (quotation.getAccount() != null) {
            modifyQuotation.setAccount(quotation.getAccount());
        }
        if (quotation.getWarehouse() != null) {
            modifyQuotation.setWarehouse(quotation.getWarehouse());
        }

        quotationRepository.save(modifyQuotation);

        ResponseModifyQuotationDTO responseModifyQuotation =
                mapper.map(modifyQuotation, ResponseModifyQuotationDTO.class);

        return responseModifyQuotation;
    }

    /* 수정필요 */
    private QuotationProduct modifyQuotationProduct(QuotationProduct product, Quotation modifyQuotation) {
        product.setQuotation(modifyQuotation);
        QuotationProduct quotationProduct = quotationProductRepository.save(product);

        return quotationProduct;
    }

    @Override
    public ResponseQuotationByCode findQuotationByCode(String quotationCode) {
        Quotation quotation = quotationRepository.findByQuotationCode(quotationCode);

        return mapper.map(quotation, ResponseQuotationByCode.class);
    }
}
