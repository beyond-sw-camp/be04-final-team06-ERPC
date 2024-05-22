package com.cineverse.erpc.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cineverse.erpc.contract.aggregate.Contract;
import com.cineverse.erpc.file.aggregate.ContractFile;
import com.cineverse.erpc.file.aggregate.NoticeFile;
import com.cineverse.erpc.file.repository.ContractFileRepository;
import com.cineverse.erpc.file.repository.NoticeFileRepository;
import com.cineverse.erpc.file.repository.QuotationFileRepository;
import com.cineverse.erpc.file.repository.TaxInvoiceFileRepository;
import com.cineverse.erpc.notice.board.aggregate.NoticeBoard;
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
    private final ContractFileRepository contractFileRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final QuotationFileRepository quotationFileRepository;
    private final TaxInvoiceFileRepository taxInvoiceFileRepository;

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
            contractFileRepository.save(contractFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contractFile.getAccessUrl();
    }

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
}
