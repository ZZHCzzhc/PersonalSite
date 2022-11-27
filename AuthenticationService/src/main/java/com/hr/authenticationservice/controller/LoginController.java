package com.hr.authenticationservice.controller;

import com.hr.authenticationservice.domain.request.LoginRequest;
import com.hr.authenticationservice.domain.request.RegistrationRequest;
import com.hr.authenticationservice.domain.response.LoginResponse;
import com.hr.authenticationservice.domain.response.RegistrationResponse;
import com.hr.authenticationservice.exception.InvalidTokenException;
import com.hr.authenticationservice.security.AuthUserDetail;
import com.hr.authenticationservice.security.JwtProvider;
import com.hr.authenticationservice.service.RegistrationTokenService;
import com.hr.authenticationservice.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anonymous")
public class LoginController {

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

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication;
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();
            String token = jwtProvider.createToken(authUserDetail);
            return LoginResponse.builder()
                    .message("Successfully Authenticated")
                    .token(token)
                    .build();
        } catch (BadCredentialsException badCredentialsException) {
            return LoginResponse.builder()
                    .message("username/password invalid")
                    .build();
        } catch (Exception e) {
            return LoginResponse.builder()
                    .message("can't login")
                    .build();
        }




    }

    @PostMapping("/Registration/{token}")
    public RegistrationResponse registration(@RequestBody RegistrationRequest request, @PathVariable String token) throws Exception {


        try {
            if (registrationTokenService.isTokenValid(token, request.getEmail())) {
                return userService.create(request.getUsername(), request.getPassword(), request.getEmail());
            }
            return RegistrationResponse.builder().message("email not fit records").build();
        } catch (InvalidTokenException invalidTokenException) {
            return RegistrationResponse.builder().message(invalidTokenException.getMessage()).build();
        } catch (Exception e) {
            return RegistrationResponse.builder().message(e.getMessage()).build();
        }
    }


}
