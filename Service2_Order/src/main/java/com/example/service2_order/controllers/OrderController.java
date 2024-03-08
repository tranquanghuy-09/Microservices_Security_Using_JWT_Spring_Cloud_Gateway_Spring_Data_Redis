package com.example.service2_order.controllers;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.service2_order.models.Order;
import com.example.service2_order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/orders")
    List<Order> getLisOrder(){
        return  orderService.getListOrder();
    }

    @GetMapping("/orders/{id}")
    Order getUserById(@PathVariable long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order, HttpSession session) {
        if (session.getAttribute("token") == null) {
            // Nếu không có token, in ra lỗi và kết thúc hàm
            System.out.println("Token not found. Please login first.");
            // Sử dụng Scanner để nhập username và password từ người dùng
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            try {
                String response = restTemplate.postForObject("http://localhost:8181/api/v1/login", requestEntity, String.class);
                return orderService.addOrder(order);
            } catch (HttpClientErrorException.BadRequest ex) {
                System.out.println("Login failed: Bad Request");
                return null;
            }
        }
        // Lấy token từ session
        String token = (String) session.getAttribute("token");

        // Tạo một HttpHeaders object và thêm token vào header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return orderService.addOrder(order);

    }
}
