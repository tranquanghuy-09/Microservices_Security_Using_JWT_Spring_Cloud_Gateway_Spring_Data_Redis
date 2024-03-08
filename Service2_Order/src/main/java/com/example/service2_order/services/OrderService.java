package com.example.service2_order.services;

import com.example.service2_order.models.Order;
import com.example.service2_order.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getListOrder(){
        return  orderRepository.findAll();
    }

    public Order getOrderById(long id){
        return orderRepository.findById(id).get();
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }
}
