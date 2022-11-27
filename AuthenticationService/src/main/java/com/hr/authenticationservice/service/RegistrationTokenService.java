package com.hr.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hr.authenticationservice.dao.Impl.RegistrationTokenDao;
import com.hr.authenticationservice.domain.RegistrationToken;
import com.hr.authenticationservice.domain.response.CreateTokenResponse;
import com.hr.authenticationservice.exception.InvalidTokenException;
import com.hr.authenticationservice.rabbitmq.MessageRequest;
import com.hr.authenticationservice.rabbitmq.TokenResponse;
import com.hr.authenticationservice.rabbitmq.service.SerializeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationTokenService {
    private final RabbitTemplate queueSender;
    RegistrationTokenDao registrationTokenDao;
//    @Autowired
//    private AmqpTemplate amqpTemplate;

    @Autowired
    public RegistrationTokenService(RabbitTemplate queueSender) {
        this.queueSender = queueSender;
    }

    @Autowired
    public void setRegistrationTokenDao(RegistrationTokenDao registrationTokenDao) {
        this.registrationTokenDao = registrationTokenDao;
    }

    public boolean isTokenValid(String token, String Email) throws Exception {
        Optional<RegistrationToken> registrationToken = Optional.ofNullable(registrationTokenDao.getRegistrationTokenByToken(token));
        if (!registrationToken.isPresent()) {
           throw new InvalidTokenException();
        }
        else {
            return registrationToken.get().getEmail().equals(Email);
        }
    }

    public CreateTokenResponse CreateRegistrationToken(int hrId, String email) throws JsonProcessingException {
        try {
            RegistrationToken registrationToken = registrationTokenDao.CreateRegistrationToken(hrId, email);


//            TokenResponse tokenResponse = TokenResponse.builder() // original message using [TokenResponse]
//                    .token(registrationToken.getToken())
//                    .email(registrationToken.getEmail())
//                    .expirationDate(registrationToken.getExpirationDate())
//                    .createBy(registrationToken.getUser().getID())
//                    .build();
//            String jsonMessage = SerializeUtil.serializeTokenResponse(tokenResponse); // original message using [TokenResponse]
//            queueSender.convertAndSend("emailExchange", "token", jsonMessage); // original message using [TokenResponse]

            String message = "Hello \n"                                          // messaging using [MessageRequest] {universal one}
                        + "Please register through the following token: \n"
                        + "registration endpoint is http://localhost:8881/anonymous/Registration/"
                        + registrationToken.getToken() + "\n"
                        + "The token will expire at "
                        + registrationToken.getExpirationDate()
                        + "\ncreateBy: "
                        + registrationToken.getUser().getID();

            MessageRequest messageRequest = MessageRequest.builder()
                    .messageContent(message)
                    .employeeEmail(registrationToken.getEmail())
                    .build();
            String jsonMessage = SerializeUtil.serializeMessageRequest(messageRequest);
            queueSender.convertAndSend("emailExchange", "token", jsonMessage); // messaging using [MessageRequest] {universal one}


//            registrationToken.setUser(User.builder().ID(registrationToken.getUser().getID()).build());  //this line will set the Response not contain creator info
            return CreateTokenResponse.builder()
                    .message("success")
                    .registrationToken(registrationToken)
                    .build();

        } catch (Exception e) {
            System.out.println("exception at service: " + e);
            return CreateTokenResponse.builder()
                    .message("failed")
                    .build();
        }
    }
}
