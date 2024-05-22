package com.cineverse.erpc.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.file.aggregate.ContractFile;
import com.cineverse.erpc.file.aggregate.NoticeFile;
import com.cineverse.erpc.file.aggregate.OrderFile;
import com.cineverse.erpc.file.aggregate.QuotationFile;
import com.cineverse.erpc.file.repository.*;
import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
import com.cineverse.erpc.order.order.aggregate.Order;
import com.cineverse.erpc.quotation.quotation.aggregate.Quotation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Transactional
    public String saveNoticeFile(MultipartFile multipartFile, NoticeBoard notice) {

        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        NoticeFile noticeFile = new NoticeFile();
        noticeFile.setOriginName(originalName);
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


}
