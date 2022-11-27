package com.hr.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hr.authenticationservice.domain.request.CreateTokenRequest;
import com.hr.authenticationservice.domain.response.CreateTokenResponse;
import com.hr.authenticationservice.security.JwtProvider;
import com.hr.authenticationservice.service.RegistrationTokenService;
import com.hr.authenticationservice.security.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HrController {

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private RegistrationTokenService registrationTokenService;
    private UserService userService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setRegistrationTokenService(RegistrationTokenService registrationTokenService) {
        this.registrationTokenService = registrationTokenService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/newToken")
    public CreateTokenResponse CreateToken(@RequestBody CreateTokenRequest request, @RequestHeader(name="Authorization") String header) throws JsonProcessingException {
        try {
            int hrId = jwtProvider.GetIdByResolveToken(header);
            return registrationTokenService.CreateRegistrationToken(hrId, request.getEmail());
        } catch (ExpiredJwtException jwtException) {
            return CreateTokenResponse.builder().message("invalid token").build();
        }catch (Exception e) {
            return CreateTokenResponse.builder().message("unauthorized").build();
        }
    }



}
