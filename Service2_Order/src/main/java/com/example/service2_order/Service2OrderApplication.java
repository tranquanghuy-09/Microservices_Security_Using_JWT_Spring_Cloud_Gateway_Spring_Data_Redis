package com.example.service2_order;

import com.example.service2_order.models.Order;
import com.example.service2_order.repositories.OrderRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Service2OrderApplication {
    private final OrderRepository orderRepository;
    private final Faker faker = new Faker();

    public Service2OrderApplication(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Service2OrderApplication.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 1; i <= 10; i++) {
                Order order = createFakeOrder();
                orderRepository.save(order);
            }
        };
    }

    private Order createFakeOrder() {
        Order order = new Order();
        order.setOrderDate(faker.date().past(10, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
        order.setStatus(faker.lorem().word());
        order.setShippingAddress(faker.address().fullAddress());
        return order;
    }
}
