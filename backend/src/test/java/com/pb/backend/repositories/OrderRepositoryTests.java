package com.pb.backend.repositories;

import com.pb.backend.entities.Order;
import com.pb.backend.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void save_ShouldPersistOrder_WhenValidOrder() {
        Order order = Factory.createOrder();

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder);
        assertEquals("abc", savedOrder.getId());
        assertEquals("PENDING", savedOrder.getStatus());
        assertEquals("Dummy Doe", savedOrder.getCustomer());
        assertEquals("Laptop", savedOrder.getProduct());
        assertEquals(2, savedOrder.getQuantity());
        assertEquals(1500.0, savedOrder.getPrice());
        assertNotNull(savedOrder.getMoment());
    }

    @Test
    void findById_ShouldReturnOrder_WhenOrderExists() {
        Order order = Factory.createOrder();
        orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById("abc");

        assertTrue(foundOrder.isPresent());
        assertEquals("abc", foundOrder.get().getId());
        assertEquals("PENDING", foundOrder.get().getStatus());
        assertEquals("Dummy Doe", foundOrder.get().getCustomer());
        assertEquals("Laptop", foundOrder.get().getProduct());
        assertEquals(2, foundOrder.get().getQuantity());
        assertEquals(1500.0, foundOrder.get().getPrice());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenOrderNotExists() {
        Optional<Order> foundOrder = orderRepository.findById("Pedido não encontrado");

        assertFalse(foundOrder.isPresent());
    }
}
