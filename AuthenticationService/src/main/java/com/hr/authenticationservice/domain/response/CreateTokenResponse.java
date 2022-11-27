package com.hr.authenticationservice.domain.response;

import com.hr.authenticationservice.domain.RegistrationToken;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTokenResponse {
    private String message;
    private RegistrationToken registrationToken;
}
