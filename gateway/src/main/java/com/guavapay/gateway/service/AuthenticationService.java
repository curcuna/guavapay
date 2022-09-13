package com.guavapay.gateway.service;

import com.guavapay.gateway.bean.dto.response.ValidateTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${service.authentication}", name = "authenticationService")
public interface AuthenticationService {

    @RequestMapping(method = RequestMethod.POST, value = "/token/validation")
    ValidateTokenResponse validateToken(@RequestHeader("Authorization")  String authorizationHeader);

}
