package com.otp.otpverification.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendOtpMessage(String to, String subject, String message) throws MessagingException {
        SimpleMailMessage helper = new SimpleMailMessage();
        helper.setFrom("maheshkumartippanu@gamil.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message);
        System.out.println("Email not sent");
        javaMailSender.send(helper);
        System.out.println("Email sent");
    }
}