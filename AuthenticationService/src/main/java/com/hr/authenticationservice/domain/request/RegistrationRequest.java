package com.hr.authenticationservice.domain.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
}
