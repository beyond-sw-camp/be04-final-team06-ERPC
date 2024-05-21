package com.cineverse.erpc.smtp.service;

import com.cineverse.erpc.employee.service.EmployeeService;
import com.cineverse.erpc.smtp.dto.RequestContractApprovalMail;
import com.cineverse.erpc.smtp.dto.RequestShipmentApprovalMail;
import com.cineverse.erpc.smtp.dto.RequestQuotationApprovalMail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmployeeService employeeService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, EmployeeService employeeService) {
        this.javaMailSender = javaMailSender;
        this.employeeService = employeeService;
    }

    @Value("${spring.mail.username}")
    private String from;

    public RequestContractApprovalMail sendContractApprovalResultMail(RequestContractApprovalMail requestMail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(requestMail.getContractApproval().getContract().getEmployee().getEmployeeEmail());
            mimeMessageHelper.setSubject(requestMail.getContractApproval().getContract().getContractCode() +
                    " 계약서의 결재 승인 여부");
            mimeMessageHelper.setText(requestMail.getContractApproval().getContract().getContractCode()
                    + " 계약서의 결재 승인 결과는 " + "\""
                    + requestMail.getContractApproval().getApprovalStatus().getApprovalStatus() + "\""
                    + " 이고 사유는 " + "\"" + requestMail.getContractApproval().getApprovalContent() + "\"" + " 입니다.");
            mimeMessageHelper.setFrom(new InternetAddress(from));

            javaMailSender.send(mimeMessage);

            return requestMail;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public RequestQuotationApprovalMail sendQuotationApprovalResultMail(RequestQuotationApprovalMail requestMail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(requestMail.getQuotationApproval().getQuotation().getEmployee().getEmployeeEmail());
            mimeMessageHelper.setSubject(requestMail.getQuotationApproval().getQuotation().getQuotationCode() +
                    " 견적서의 결재 승인 여부");
            mimeMessageHelper.setText(requestMail.getQuotationApproval().getQuotation().getQuotationCode()
                    + " 견적서의 결재 승인 결과는 " + "\""
                    + requestMail.getQuotationApproval().getApprovalStatus().getApprovalStatus() + "\""
                    + " 이고 사유는 " + "\"" + requestMail.getQuotationApproval().getApprovalContent() + "\"" + " 입니다.");
            mimeMessageHelper.setFrom(new InternetAddress(from));

            javaMailSender.send(mimeMessage);

            return requestMail;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public RequestShipmentApprovalMail sendShipmentApprovalResultMail(RequestShipmentApprovalMail requestMail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(requestMail.getShipmentApproval().getOrder().getEmployee().getEmployeeEmail());
            mimeMessageHelper.setSubject(requestMail.getShipmentApproval().getOrder().getTransaction()
                    .getTransactionCode() + " 수주의 결재 승인 여부");
            mimeMessageHelper.setText(requestMail.getShipmentApproval().getOrder().getTransaction()
                    .getTransactionCode() + " 수주의 결재 승인 결과는 " + "\""
                    + requestMail.getShipmentApproval().getApprovalStatus().getApprovalStatus() + "\""
                    + " 이고 사유는 " + "\"" + requestMail.getShipmentApproval().getApprovalContent() + "\"" + " 입니다.");
            mimeMessageHelper.setFrom(new InternetAddress(from));

            javaMailSender.send(mimeMessage);

            return requestMail;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
