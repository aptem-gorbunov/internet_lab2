package com.example._2lab2_8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendHTMLMessage(String to, String subject, String text) throws MessagingException {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(to);
//        message.setSubject(subject);
//
//        message.setText(text);
//        emailSender.send(message);
//        ...


        MimeMessage message = mailSender.createMimeMessage();

// use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);

// use the true flag to indicate the text included is HTML
        helper.setText("<html><body><a href=\"http://localhost:8080/verify/"+text+"\">Verify</a></body></html>", true);

        mailSender.send(message);
    }
}