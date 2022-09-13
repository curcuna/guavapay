package com.guavapay.orderservice.controller;

import com.guavapay.orderservice.model.entity.Order;
import com.guavapay.orderservice.model.entity.OrderStatus;
import com.guavapay.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity saveOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity cancelOrder(@PathVariable long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @PutMapping(value = "/status")
    public ResponseEntity changeOrderStatus(@RequestBody Order order) {
        return orderService.changeOrderStatus(order);
    }

    @PutMapping(value = "/destination")
    public ResponseEntity changeOrderDestination(@RequestBody Order order) {
        return orderService.changeOrderDestination(order);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity getOrder(@PathVariable long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping(value = "/person/{personId}")
    public ResponseEntity getOrders(@PathVariable long personId) {
        return orderService.getOrders(personId);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity getAllOrders() {
        return orderService.getAllOrders();
    }
}