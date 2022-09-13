package com.guavapay.gateway.config;

import com.guavapay.gateway.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class GatewayConfig {

    @Value("${service.authentication}")
    String authenticationService;

    @Value("${service.order}")
    String orderService;

    @Value("${service.cargo}")
    String cargoService;

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/authentication/**")
                        .filters(f -> f.filter(filter))
                        .uri(authenticationService))
                .route("order-service", r -> r.path("/order/**")
                        .filters(f -> f.filter(filter))
                        .uri(orderService))
                .route("cargo-service", r -> r.path("/cargo/**")
                        .filters(f -> f.filter(filter))
                        .uri(cargoService))
                .build();
    }
}