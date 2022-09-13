package com.guavapay.gateway.security;

import com.guavapay.gateway.bean.dto.response.ValidateTokenResponse;
import com.guavapay.gateway.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@CrossOrigin
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("*****************************");
        System.out.println("*****************************");
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
            System.out.println("security check is needed.");
            if (this.isAuthorizationHeaderMissing(request)){
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            System.out.println("token is " + token);
            try {
                ValidateTokenResponse validateTokenResponse =
                        authenticationService.validateToken(token);
                System.out.println("token is validated");
                if(!routerValidator.hasAccessRight.test(request, validateTokenResponse.getRoleSet())){
                    System.out.println("has NOT access right");
                    return this.onError(exchange, HttpStatus.UNAUTHORIZED);
                }
            } catch (Exception ex) {
                return this.onError(exchange, HttpStatus.UNAUTHORIZED);
            }
        }
        System.out.println("everything is ok");
        return chain.filter(exchange);
    }

    private boolean isAuthorizationHeaderMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }
}