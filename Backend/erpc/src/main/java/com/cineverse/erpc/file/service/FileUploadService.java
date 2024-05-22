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
    public void deleteNoticeFiles(long noticeBoardId) {
        List<NoticeFile> files = noticeFileRepository.findByNotice_NoticeId(noticeBoardId);
        System.out.println("Found files to delete: " + files.size());

        if (!files.isEmpty()) {
            for (NoticeFile file : files) {
                System.out.println("Preparing to delete file ID: " + file.getFileId());
                noticeFileRepository.delete(file);
                System.out.println("Deleted file ID from DB: " + file.getFileId());
            }
            entityManager.flush();
            entityManager.clear();

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    for (NoticeFile file : files) {
                        try {
                            amazonS3Client.deleteObject(bucketName, file.getStoredName());
                            System.out.println("Successfully deleted from S3: " + file.getStoredName());
                        } catch (Exception e) {
                            System.out.println("Error deleting file from S3: " + e.getMessage());
                        }
                    }
                }
            });
        } else {
            System.out.println("No files found to delete.");
        }
    }


//    @Transactional
//    public void deleteNoticeFiles(NoticeBoard noticeBoard) {
////        noticeFileRepository.deleteAllByNotice_NoticeId(noticeBoard.getNoticeId());
////        List<NoticeFile> files = noticeFileRepository.findByNotice_NoticeId(noticeBoard.getNoticeId());
//        noticeFileRepository.deleteByStoredName(noticeBoard.getNoticeFile().)
//        entityManager.flush();  // 현재 세션의 상태를 데이터베이스에 동기화
//        entityManager.clear();  // 세션 캐시를 클리어
//
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//            @Override
//            public void afterCommit() {
//                for (NoticeFile file : files) {
//                    try {
//                        amazonS3Client.deleteObject(bucketName, file.getOriginName());
//                        System.out.println("File deleted successfully from S3: " + file.getOriginName());
//                    } catch (Exception e) {
//                        System.out.println("Error deleting file from S3: " + e.getMessage());
//                    }
//                }
//            }
//        });
//    }

}
