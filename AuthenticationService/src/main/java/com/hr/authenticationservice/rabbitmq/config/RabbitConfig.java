package com.hr.authenticationservice.rabbitmq.config;
//import com.hr.authenticationservice.rabbitmq.listener.RabbitListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    ConnectionFactory connectionFactory(){
        // setup rabbit info before use
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

//    Queue queue1(){
//        return QueueBuilder.durable("tokenQueue").build();
//    }

//    Queue queue2(){
//        return QueueBuilder.durable("Queue2").build();
//    }
//
//    Queue queue3(){
//        return QueueBuilder.durable("Queue3").build();
//    }

//    @Bean
//    RabbitListener rabbitListener(){
//        return new RabbitListener();
//    }

//    @Bean
//    MessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
//        messageListenerContainer.setConnectionFactory(connectionFactory());
//        messageListenerContainer.setQueues(queue1());
//        messageListenerContainer.setMessageListener(rabbitListener());
//        return messageListenerContainer;
//    }

}

