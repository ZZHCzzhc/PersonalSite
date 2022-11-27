package com.hr.authenticationservice.domain.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreateTokenRequest {
    private String email;
}
