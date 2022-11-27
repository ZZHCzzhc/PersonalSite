package com.hr.authenticationservice.rabbitmq.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
//    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    public void sendGenericEmailMessage(SimpleMailMessage message);
}
