package com.hr.authenticationservice.rabbitmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("noreply@zhcTEST.com");
        mail.setTo(message.getTo());
        mail.setSubject("registration link from hr");
        mail.setText(message.getText());
        emailSender.send(mail);
    }
}