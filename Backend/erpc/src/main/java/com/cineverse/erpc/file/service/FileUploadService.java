package com.cineverse.erpc.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.file.aggregate.*;
import com.cineverse.erpc.file.repository.*;
import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import com.cineverse.erpc.slip.taxinvoice.aggreagte.TaxInvoiceRequest;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private static String bucketName = "erpc-bucket";

    private final AmazonS3Client amazonS3Client;
    private final NoticeFileRepository noticeFileRepository;
    private final QuotationFileRepository quotationFileRepository;
    private final ContractFileRepository contractFileRepository;
    private final OrderFileRepository orderFileRepository;
    private final TaxInvoiceFileRepository taxInvoiceFileRepository;
    private final EntityManager entityManager;

    @Transactional
    public String saveNoticeFile(MultipartFile multipartFile, NoticeBoard notice) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        NoticeFile noticeFile = new NoticeFile();
        noticeFile.setOriginName(originalName);
        noticeFile.setStoredName(storedName);
        noticeFile.setNotice(notice);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();
            noticeFile.setAccessUrl(accessUrl);
            noticeFile.setUploadDate(notice.getNoticeDate());
            noticeFile.setUploadUser(notice.getEmployee().getEmployeeCode());

            noticeFileRepository.save(noticeFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return noticeFile.getAccessUrl();
    }

    @Transactional
    public String saveQuotationFile(MultipartFile multipartFile, Quotation quotation) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        QuotationFile quotationFile = new QuotationFile();
        quotationFile.setOriginName(originalName);
        quotationFile.setStoredName(storedName);
        quotationFile.setQuotation(quotation);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();

            quotationFile.setAccessUrl(accessUrl);
            quotationFile.setUploadDate(quotation.getQuotationDate());
            quotationFile.setUploadUser(quotation.getEmployee().getEmployeeCode());
            quotationFileRepository.save(quotationFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return quotationFile.getAccessUrl();
    }

    @Transactional
    public String saveContractFile(MultipartFile multipartFile, Contract contract) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        ContractFile contractFile = new ContractFile();
        contractFile.setOriginName(originalName);
        contractFile.setStoredName(storedName);
        contractFile.setContract(contract);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();

            contractFile.setAccessUrl(accessUrl);
            contractFile.setUploadDate(contract.getContractDate());
            contractFile.setUploadUser(contract.getEmployee().getEmployeeCode());
            contractFileRepository.save(contractFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contractFile.getAccessUrl();
    }

    @Transactional
    public String saveOrderFile(MultipartFile multipartFile, Order order) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        OrderFile orderFile = new OrderFile();
        orderFile.setOriginName(originalName);
        orderFile.setStoredName(storedName);
        orderFile.setOrder(order);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();

            orderFile.setAccessUrl(accessUrl);
            orderFile.setUploadDate(order.getOrderDate());
            orderFile.setUploadUser(order.getEmployee().getEmployeeCode());
            orderFileRepository.save(orderFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orderFile.getAccessUrl();
    }

    @Transactional
    public String saveTaxInvoiceFile(MultipartFile multipartFile, TaxInvoiceRequest taxInvoice) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        TaxInvoiceFile taxFile = new TaxInvoiceFile();
        taxFile.setOriginName(originalName);
        taxFile.setStoredName(storedName);
        taxFile.setTaxInvoiceRequest(taxInvoice);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();
            taxFile.setAccessUrl(accessUrl);
            taxFile.setUploadDate(taxInvoice.getTaxInvoiceRequestDate());
            taxFile.setUploadUser(taxInvoice.getEmployee().getEmployeeCode());

            taxInvoiceFileRepository.save(taxFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return taxFile.getAccessUrl();
    }

    @Transactional
    public void deleteFilesByNotice(NoticeBoard noticeBoard) {
        List<NoticeFile> noticeFiles = noticeFileRepository.findByNotice_NoticeId(noticeBoard.getNoticeId());

        for (NoticeFile noticeFile : noticeFiles) {
            noticeFile.setNotice(null);
            noticeFileRepository.deleteById(noticeFile.getFileId());
            amazonS3Client.deleteObject(bucketName, noticeFile.getStoredName());
        }
        noticeBoard.getNoticeFile().clear();
    }

    @Transactional
    public void deleteFilesByQuotation(Quotation quotation) {
        List<QuotationFile> quotationFiles = quotationFileRepository.
                findByQuotation_QuotationId(quotation.getQuotationId());

        for (QuotationFile quotationFile : quotationFiles) {
            quotationFile.setQuotation(null);
            quotationFileRepository.deleteById(quotationFile.getFileId());
            amazonS3Client.deleteObject(bucketName, quotationFile.getStoredName());
        }
        quotation.getQuotationFile().clear();
    }

    @Transactional
    public void deleteFilesByContract(Contract contract) {
        List<ContractFile> contractFiles = contractFileRepository.findByContract_ContractId(contract.getContractId());

        for (ContractFile contractFile : contractFiles) {
            contractFile.setContract(null);
            contractFileRepository.deleteById(contractFile.getFileId());
            amazonS3Client.deleteObject(bucketName, contractFile.getStoredName());
        }
        contract.getContractFile().clear();
    }

    @Transactional
    public void deleteFilesByOrder(Order order) {
        List<OrderFile> orderFiles = orderFileRepository.findByOrder_OrderRegistrationId(order.getOrderRegistrationId());

        for (OrderFile orderFile : orderFiles) {
            orderFile.setOrder(null);
            orderFileRepository.deleteById(orderFile.getFileId());
            amazonS3Client.deleteObject(bucketName, orderFile.getStoredName());
        }
        order.getOrderFile().clear();
    }

    @Transactional
    public void deleteFilesByTaxInvoice(TaxInvoiceRequest taxInvoice) {
        List<TaxInvoiceFile> taxInvoiceFiles =
                taxInvoiceFileRepository.findByTaxInvoiceRequest_TaxInvoiceRequestId(taxInvoice.getTaxInvoiceRequestId());

        for (TaxInvoiceFile taxInvoiceFile : taxInvoiceFiles) {
            taxInvoiceFile.setTaxInvoiceRequest(null);
            taxInvoiceFileRepository.deleteById(taxInvoiceFile.getFileId());
            amazonS3Client.deleteObject(bucketName, taxInvoiceFile.getStoredName());
        }
        taxInvoice.getTaxInvoiceFile().clear();
    }
}
