package com.guavapay.orderservice.listener;

import com.guavapay.orderservice.model.entity.Order;
import com.guavapay.orderservice.model.entity.OrderStatus;
import com.guavapay.orderservice.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private OrderRepository orderRepository;

    @Autowired
    public OrderListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "assignCourier")
    public void assignCourier( long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.ASSIGNED);
        this.orderRepository.save(order);
    }

    @RabbitListener(queues = "changeOrderStatus")
    public void changeStatus( long orderId){
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.COMPLETED);
        this.orderRepository.save(order);
    }
}