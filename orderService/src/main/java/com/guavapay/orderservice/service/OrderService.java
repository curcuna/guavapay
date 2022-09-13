package com.guavapay.orderservice.service;

import com.guavapay.orderservice.model.entity.Order;
import com.guavapay.orderservice.model.entity.OrderStatus;
import com.guavapay.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity createOrder(Order order) {
        order.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(order);
        return ResponseEntity.ok().body(order);
    }

    public ResponseEntity cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
        return ResponseEntity.ok().body(order);
    }

    public ResponseEntity changeOrderDestination(Order order) {
        Order _order = orderRepository.findById(order.getId()).get();
        _order.setDestination(order.getDestination());
        orderRepository.save(_order);
        return ResponseEntity.ok().body(_order);
    }

    public ResponseEntity changeOrderStatus(Order order) {
        Order _order = orderRepository.findById(order.getId()).get();
        _order.setOrderStatus(order.getOrderStatus());
        orderRepository.save(_order);
        return ResponseEntity.ok().body(_order);
    }


    public ResponseEntity getOrder(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return ResponseEntity.ok().body(order);
    }

    public ResponseEntity getOrders(long personId) {
        List<Order> orderList = orderRepository.findByPersonId(personId);
        return ResponseEntity.ok().body(orderList);
    }

    public ResponseEntity getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return ResponseEntity.ok().body(orderList);
    }
}
