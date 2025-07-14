package com.pb.backend.tests;

import com.pb.backend.dto.OrderDTO;
import com.pb.backend.entities.Order;

import java.time.Instant;

public class Factory {
    public static Order createOrder() {
        Order order = new Order();
        order.setId("abc");
        order.setMoment(Instant.now());
        order.setStatus("PENDING");
        order.setCustomer("Dummy Doe");
        order.setProduct("Laptop");
        order.setQuantity(2);
        order.setPrice(1500.0);
        return order;
    }

    public static OrderDTO createOrderDTO() {
        Order order = createOrder();
        return new OrderDTO(order);
    }

    public static Order createCustomOrder(String status, String customer, String product, int quantity, double price) {
        Order order = new Order();
        order.setId("123");
        order.setMoment(Instant.now());
        order.setStatus(status);
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setPrice(price);
        return order;
    }

    public static OrderDTO createCustomOrderDTO(String status, String customer, String product, int quantity, double price) {
        Order order = createCustomOrder(status, customer, product, quantity, price);
        return new OrderDTO(order);
    }
}
