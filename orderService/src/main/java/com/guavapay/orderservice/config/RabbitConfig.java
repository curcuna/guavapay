package com.guavapay.orderservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue assignCourier() {
        return new Queue("assignCourier", false);
    }

    @Bean
    public Queue changeOrderStatus() {
        return new Queue("changeOrderStatus", false);
    }

    @Bean
    public Queue changeLocation() {
        return new Queue("changeLocation", false);
    }

}
