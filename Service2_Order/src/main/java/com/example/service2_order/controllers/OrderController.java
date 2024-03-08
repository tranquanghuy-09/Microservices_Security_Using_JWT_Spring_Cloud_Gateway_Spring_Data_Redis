package com.example.service2_order.controllers;

import com.example.service2_order.models.Order;
import com.example.service2_order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    List<Order> getListUser(){
        return  orderService.getListUser();
    }

    @GetMapping("/orders/{id}")
    Order getUserById(@PathVariable long id){
        return orderService.getUserById(id);
    }
}
