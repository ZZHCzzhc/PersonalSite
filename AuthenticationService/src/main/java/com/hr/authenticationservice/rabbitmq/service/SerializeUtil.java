package com.hr.authenticationservice.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.authenticationservice.rabbitmq.MessageRequest;
import com.hr.authenticationservice.rabbitmq.TokenResponse;

public class SerializeUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();


    public static String serializeTokenResponse(TokenResponse tokenResponse) {
        String result = null;

        try {
            result = objectMapper.writeValueAsString(tokenResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public static TokenResponse deserializeTokenResponse(String jsonMsg){
        TokenResponse tokenResponse = null;

        try {
            tokenResponse = objectMapper.readValue(jsonMsg, TokenResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return tokenResponse;
    }

    public static String serializeMessageRequest(MessageRequest messageRequest) {
        String result = null;

        try {
            result = objectMapper.writeValueAsString(messageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static MessageRequest deserializeMessageRequest(String jsonMsg){
        MessageRequest messageRequest = null;

        try {
            messageRequest = objectMapper.readValue(jsonMsg, MessageRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return messageRequest;
    }

}
