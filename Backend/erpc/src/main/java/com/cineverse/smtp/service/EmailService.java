package com.cineverse.smtp.service;

import com.cineverse.erpc.employee.service.EmployeeService;
import com.cineverse.smtp.dto.ApprovalMessage;
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

    public ApprovalMessage sendApprovalResultMail(ApprovalMessage approvalMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject("비밀번호 재설정");
            mimeMessageHelper.setText("재설정된 비밀번호: " + newPwd);
            mimeMessageHelper.setFrom(new InternetAddress(from));

            javaMailSender.send(mimeMessage);
            memberService.changePwd(newPwd, emailMessage.getTo());

            emailMessage.setMessage("재설정된 비밀번호: " + newPwd);
            emailMessage.setSubject("비밀번호 재설정");

            return emailMessage;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
