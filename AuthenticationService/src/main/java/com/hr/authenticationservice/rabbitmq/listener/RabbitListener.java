package com.hr.authenticationservice.rabbitmq.listener;

import com.hr.authenticationservice.rabbitmq.TokenResponse;
import com.hr.authenticationservice.rabbitmq.service.EmailServiceImpl;
import com.hr.authenticationservice.rabbitmq.service.SerializeUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

//public class RabbitListener implements MessageListener {
//    EmailServiceImpl emailService;
//
//    @Autowired
//    public void setEmailService(EmailServiceImpl emailService) {
//        this.emailService = emailService;
//    }
////    @RabbitListener(queues = "queueName")
////    public void receive(String message) {
////        System.out.println(message);
////    }
//    @Override
//    public void onMessage(Message message) {
////        System.out.println("New message received from " + message.getMessageProperties().getConsumerQueue() + ": " + new String(message.getBody()));
//        String body = new String(message.getBody());
//        if (body != "") {
//            try {
//                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//                TokenResponse tokenResponse = SerializeUtil.deserializeTokenResponse(body);
//
//                simpleMailMessage.setText("Hello \n"
//                        + "Please register through the following token: "
//                        + "registration endpoint is http://localhost:8881/anonymous/Registration/"
//                        + tokenResponse.getToken() + "\n"
//                        + "The token will expire at "
//                        + tokenResponse.getExpirationDate()
//                        + "createBy: "
//                        + tokenResponse.getCreateBy());
//                simpleMailMessage.setTo(tokenResponse.getEmail());
//                // sending email
//                emailService.sendGenericEmailMessage(simpleMailMessage);
//
//            } catch (Exception e) {
//                throw e;
////            System.out.println(e);
//            }
//        }
//
//    }
//}
