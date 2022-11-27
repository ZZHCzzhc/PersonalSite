package com.hr.authenticationservice.rabbitmq;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageRequest implements Serializable {
    private String messageContent;
    private String employeeEmail;
}
