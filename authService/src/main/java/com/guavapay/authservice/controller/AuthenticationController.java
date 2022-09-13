package com.guavapay.authservice.controller;

import com.guavapay.authservice.model.dto.request.LoginRequest;
import com.guavapay.authservice.model.dto.request.SignupRequest;
import com.guavapay.authservice.model.dto.request.ValidateTokenRequest;
import com.guavapay.authservice.model.dto.response.LoginResponse;
import com.guavapay.authservice.model.dto.response.ValidateTokenResponse;
import com.guavapay.authservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        return authenticationService.signup(signupRequest);
    }

    @PostMapping(value = "/signup/courier")
    public ResponseEntity createCourier(@RequestBody SignupRequest signupRequest) {
        return authenticationService.createCourier(signupRequest);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/token/validation")
    public ValidateTokenResponse validateJwt(@RequestHeader("Authorization") String authorizationHeader) {
        ValidateTokenResponse validateTokenResponse = authenticationService.validateJwt(authorizationHeader);
        return validateTokenResponse;
    }

    @GetMapping(value = "/courier")
    public ResponseEntity getAllCouriers() {
        return authenticationService.getAllCouriers();
    }
}