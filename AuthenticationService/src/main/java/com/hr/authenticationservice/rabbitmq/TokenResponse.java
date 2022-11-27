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
public class TokenResponse implements Serializable {
    String token;
    Timestamp expirationDate;
    String email;
    int createBy;
}
